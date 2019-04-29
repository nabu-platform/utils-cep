package be.nabu.utils.cep.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import be.nabu.libs.converter.ConverterFactory;
import be.nabu.libs.evaluator.ContextAccessorFactory;
import be.nabu.libs.evaluator.api.AnnotatedContextAccessor;
import be.nabu.libs.evaluator.api.ContextAccessor;
import be.nabu.libs.evaluator.api.ListableContextAccessor;
import be.nabu.utils.cep.api.CommonEvent;
import be.nabu.utils.cep.api.CommonEventExtension;
import be.nabu.utils.cep.api.ComplexEvent;
import be.nabu.utils.cep.api.Severity;

public class CEFUtils {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void formatAsCEF(Appendable target, String deviceVendor, String deviceProduct, String deviceVersion, Iterable<Object> events) {
		boolean firstEvent = true;
		for (Object event : events) {
			try {
				if (firstEvent) {
					firstEvent = false;
				}
				else {
					target.append("\n");
				}
				// version fixed to 0
				target.append("CEF:0|");
				target.append(escapeField(deviceVendor));
				target.append("|");
				target.append(escapeField(deviceProduct));
				target.append("|");
				target.append(escapeField(deviceVersion));
				target.append("|");
	
				String signatureId = null;
				if (event instanceof ComplexEvent) {
					if (((ComplexEvent) event).getLocalId() != null) {
						signatureId = ((ComplexEvent) event).getLocalId();
					}
					else if (((ComplexEvent) event).getEventName() != null) {
						signatureId = ((ComplexEvent) event).getEventName();
					}
					if (((ComplexEvent) event).getArtifactId() != null) {
						if (signatureId == null) {
							signatureId = ((ComplexEvent) event).getArtifactId();
						}
						else {
							signatureId = ((ComplexEvent) event).getArtifactId() + ":" + signatureId;
						}
					}
				}
				if (signatureId == null) {
					signatureId = event.getClass().getName();
				}
				// event class id / signature id
				target.append(escapeField(signatureId));
				target.append("|");
				
				String eventName = null;
				if (event instanceof ComplexEvent) {
					eventName = ((ComplexEvent) event).getEventName();
				}
				if (eventName == null) {
					eventName = event.getClass().getSimpleName();
				}
				
				// readable event name
				target.append(escapeField(eventName));
				target.append("|");
				
				// the severity
				Severity severity = null;
				if (event instanceof ComplexEvent) {
					severity = ((ComplexEvent) event).getSeverity();
				}
				if (severity == null) {
					severity = Severity.MEDIUM;
				}
				
				target.append(Integer.toString(severity.getTo()));
				target.append("|");
				
				ContextAccessor accessor = ContextAccessorFactory.getInstance().getAccessor(event.getClass());
				if (accessor instanceof ListableContextAccessor) {
					boolean first = true;
					for (String field : (Collection<String>) ((ListableContextAccessor) accessor).list(event)) {
						String key = field;
						if (accessor instanceof AnnotatedContextAccessor) {
							Map annotation = ((AnnotatedContextAccessor) accessor).getAnnotation(event, field, "CEFIgnore");
							if (annotation != null) {
								continue;
							}
							annotation = ((AnnotatedContextAccessor) accessor).getAnnotation(event, field, "CEFField");
							if (annotation.get("key") != null) {
								key = annotation.get("key").toString();
							}
						}
						Object value = accessor.get(event, field);
						if (value != null) {
							if (first) {
								first = false;
							}
							else {
								target.append(" ");
							}
							appendExtension(target, key, value);
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	
	protected static Iterable<CommonEvent> parse(InputStream input) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd HH:mm:ss", Locale.US);
		
		List<CommonEvent> events = new ArrayList<CommonEvent>();
		InputStreamReader reader = new InputStreamReader(input, Charset.forName("UTF-8"));
		BufferedReader buffered = new BufferedReader(reader);
		try {
			String line = null;
			while ((line = buffered.readLine()) != null) {
				if (line.trim().isEmpty() || line.startsWith("#")) {
					continue;
				}
				int index = line.indexOf("CEF:");
				CommonEvent event = null;
				// syslog format
				if (index > 0) {
					Date created = formatter.parse(line.substring(0, 15));
					String host = line.substring(15, index).trim();
					event = parseEvent(line.substring(index), created, host);
				}
				// not syslog format
				else if (index == 0) {
					event = parseEvent(line, null, null);
				}
				// not a CEF line
				else {
					continue;
				}
				if (event != null) {
					events.add(event);
				}
			}
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				buffered.close();
			}
			catch(IOException e) {
				throw new RuntimeException(e);
			}
		}
		return events;
	}
	
	protected static CommonEvent parseEvent(String substring, Date created, String host) {
		return null;
	}

	protected static void format(OutputStream output, Iterable<CommonEvent> events, boolean syslog) {
		// in local time
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd HH:mm:ss", Locale.US);
		
		String host;
		try {
			host = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e) {
			host = "anonymous";
		}
		
		OutputStreamWriter writer = new OutputStreamWriter(output, Charset.forName("UTF-8"));
		BufferedWriter buffered = new BufferedWriter(writer);
		try {
			// CEF:Version|Device Vendor|Device Product|Device Version|Device Event Class ID|Name|Severity|[Extension]
			// pipes in extension don't need escaping, others do
			// Sep 19 08:26:10 host CEF:0|security|threatmanager|1.0|100|detected a \| in message|10|src=10.0.0.1 act=blocked a | dst=1.1.1.1
			for (CommonEvent event : events) {
				if (syslog) {
					buffered.append(formatter.format(event.getCreated()));
					buffered.append(" ");
					buffered.append(event.getHost() != null ? event.getHost() : host);
					buffered.append(" ");
				}
				buffered.append("CEF:");
				buffered.append(event.getVersion() != null ? event.getVersion().toString() : "0");
				buffered.append("|");
				buffered.append(escapeField(event.getDeviceVendor()));
				buffered.append("|");
				buffered.append(escapeField(event.getDeviceProduct()));
				buffered.append("|");
				buffered.append(escapeField(event.getDeviceVersion()));
				buffered.append("|");
				buffered.append(escapeField(event.getDeviceEventClassId()));
				buffered.append("|");
				buffered.append(escapeField(event.getName()));
				buffered.append("|");
				buffered.append(Integer.toString(event.getSeverity() == null ? Severity.MEDIUM.getTo() : event.getSeverity().getTo()));
				buffered.append("|");
				boolean first = true;
				if (event.getExtensions() == null || !event.getExtensions().containsKey(CommonEventExtension.TIMEZONE.getName())) {
					appendExtension(buffered, CommonEventExtension.TIMEZONE.getName(), TimeZone.getDefault());
					first = false;
				}
				if (!syslog && (event.getExtensions() == null || !event.getExtensions().containsKey(CommonEventExtension.CREATED.getName()))) {
					if (!first) {
						buffered.append(" ");
					}
					first = false;
					appendExtension(buffered, CommonEventExtension.CREATED.getName(), event.getCreated().getTime());
				}
				if (!syslog && (event.getExtensions() == null || !event.getExtensions().containsKey(CommonEventExtension.HOST.getName()))) {
					if (!first) {
						buffered.append(" ");
					}
					first = false;
					appendExtension(buffered, CommonEventExtension.HOST.getName(), host);
				}
				for (Map.Entry<String, Object> entry : event.getExtensions().entrySet()) {
					if (first) {
						first = false;
					}
					else {
						buffered.append(" ");
					}
					appendExtension(buffered, entry.getKey(), entry.getValue());
				}
				buffered.append("\n");
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			try {
				buffered.flush();
			}
			catch (IOException e) {
				// not a lot to do about it..
				throw new RuntimeException(e);
			}
		}
	}
	
	private static void appendExtension(Appendable writer, String key, Object value) throws IOException {
		if (!(value instanceof String)) {
			value = ConverterFactory.getInstance().getConverter().convert(value, String.class);
		}
		writer.append(escapeExtension(key)).append("=").append(escapeExtension((String) value));
	}
	private static String escapeField(String value) {
		return value.replace("\\", "\\\\")
			.replace("|", "\\|")
			.replaceAll("[\r\n]+", "\n");
	}
	private static String escapeExtension(String value) {
		return value.replace("\\", "\\\\")
			.replace("=", "\\=")
			.replaceAll("[\r\n]+", "\n");
	}
}
