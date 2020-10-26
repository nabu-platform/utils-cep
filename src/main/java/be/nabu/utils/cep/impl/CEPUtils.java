package be.nabu.utils.cep.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import be.nabu.libs.converter.ConverterFactory;
import be.nabu.libs.evaluator.api.AnnotatedContextAccessor;
import be.nabu.libs.evaluator.api.ContextAccessor;
import be.nabu.libs.evaluator.api.ListableContextAccessor;
import be.nabu.libs.evaluator.impl.JavaContextAccessor;
import be.nabu.utils.cep.api.CEPField;
import be.nabu.utils.cep.api.CEPIdentifiable;
import be.nabu.utils.cep.api.CEPIgnore;
import be.nabu.utils.cep.api.CommonEvent;
import be.nabu.utils.cep.api.CommonEventExtension;
import be.nabu.utils.cep.api.ComplexEvent;
import be.nabu.utils.cep.api.EventSeverity;

public class CEPUtils {
	
	public static NetworkedComplexEventImpl newServerNetworkEvent(Class<?> context, String name, SocketAddress address) {
		return newServerNetworkEvent(context, name, address, null, null);
	}
	
	public static NetworkedComplexEventImpl newServerNetworkEvent(Class<?> context, String name, SocketAddress address, String message, Exception e) {
		NetworkedComplexEventImpl event = new NetworkedComplexEventImpl();
		enrich(event, context, name, address, message, e);
		return event;
	}

	public static void enrich(NetworkedComplexEventImpl event, Class<?> context, String name, SocketAddress address, String message, Exception e) {
		// it's an assumption that holds true in most cases
		event.setTransportProtocol("TCP");
		event.setCreated(new Date());
		event.setMessage(message);
		event.setEventName(name);
		event.setContext(context.getName());
		event.setLocalId(context.getName() + ":" + name);
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			event.setDestinationHost(localHost.getHostName());
			event.setDestinationIp(localHost.getHostAddress());
			// same host for server events
			event.setServerHost(event.getDestinationHost());
		}
		catch (UnknownHostException ue) {
			// ignore
		}
		if (e != null) {
			event.setSeverity(EventSeverity.ERROR);
			CEPUtils.enrich(event, e);
		}
		else {
			event.setSeverity(EventSeverity.INFO);
		}
		if (address instanceof InetSocketAddress) {
			InetSocketAddress socketAddress = (InetSocketAddress) address;
			if (socketAddress.getAddress() instanceof Inet6Address) {
				event.setNetworkProtocol("ipv6");
			}
			else {
				event.setNetworkProtocol("ipv4");
			}
			event.setSourceHost(socketAddress.getHostName());
			event.setSourceIp(socketAddress.getAddress().getHostAddress());
			event.setSourcePort(socketAddress.getPort());
		}
	}
	
	public static ComplexEventImpl enrich(ComplexEventImpl event, Exception e) {
		StringWriter stringWriter = new StringWriter();
		PrintWriter printer = new PrintWriter(stringWriter);
		e.printStackTrace(printer);
		printer.flush();
		event.setStacktrace(stringWriter.toString());
		event.setSeverity(EventSeverity.ERROR);
		return event;
	}
	
	private static String hash(String content) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update((byte[]) content.getBytes("UTF-8"));
			byte [] hash = digest.digest();
			StringBuilder string = new StringBuilder();
			for (int i = 0; i < hash.length; i++) {
				string.append(Integer.toHexString((hash[i] & 0xFF) | 0x100).substring(1,3));
			}
			return string.toString();
		}
		catch (Exception e) {
			e.printStackTrace(System.err);
			return "failed-hash";
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void asCEF(Appendable target, String deviceVendor, String deviceProduct, String deviceVersion, boolean hashIdentifiable, Iterable<Object> events) {
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
				EventSeverity severity = null;
				if (event instanceof ComplexEvent) {
					severity = ((ComplexEvent) event).getSeverity();
				}
				
				target.append(Integer.toString(severity == null ? 5 : severity.getLevel()));
				target.append("|");
				
				// TODO: use the factory, however it defaults to the JavaBeanAccessor which (currently) does not support listing & annotations
				ContextAccessor accessor = new JavaContextAccessor();
				boolean first = true;
				if (accessor instanceof ListableContextAccessor) {
					List<String> list = new ArrayList<String>((Collection<String>) ((ListableContextAccessor) accessor).list(event));
					// alphabetical predictability
					Collections.sort(list);
					for (String field : list) {
						String key = field;
						Object value = accessor.get(event, field);
						if (value != null) {
							if (accessor instanceof AnnotatedContextAccessor) {
								Map annotation = ((AnnotatedContextAccessor) accessor).getAnnotation(event, field, CEPIgnore.class.getSimpleName());
								if (annotation != null) {
									continue;
								}
								annotation = ((AnnotatedContextAccessor) accessor).getAnnotation(event, field, CEPField.class.getSimpleName());
								if (annotation != null && annotation.get("key") != null) {
									key = annotation.get("key").toString();
								}
								if (hashIdentifiable) {
									annotation = ((AnnotatedContextAccessor) accessor).getAnnotation(event, field, CEPIdentifiable.class.getSimpleName());
									if (annotation != null) {
										if (!(value instanceof String)) {
											value = ConverterFactory.getInstance().getConverter().convert(value, String.class);
										}
										value = hash((String) value);
									}
								}
							}
						
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
				if (event instanceof ComplexEvent) {
					Map<String, Object> extensions = ((ComplexEvent) event).getExtensions();
					if (extensions != null) {
						for (String key : extensions.keySet()) {
							if (first) {
								first = false;
							}
							else {
								target.append(" ");
							}
							if (extensions.get(key) != null) {
								appendExtension(target, key, extensions.get(key));
							}
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
	
	public static List<CommonEvent> parse(InputStream input) {
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
				buffered.append(Integer.toString(event.getSeverity() == null ? EventSeverity.INFO.getLevel() : event.getSeverity().getLevel()));
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
			String converted = ConverterFactory.getInstance().getConverter().convert(value, String.class);
			value = converted == null ? value.toString() : converted;
		}
		writer.append(escapeExtension(key)).append("=").append(escapeExtension((String) value));
	}
	private static String escapeField(String value) {
		return value.replace("\\", "\\\\")
			.replace("|", "\\|")
			.replaceAll("[\r\n]+", "\\\\n");
	}
	private static String escapeExtension(String value) {
		return value.replace("\\", "\\\\")
			.replace("=", "\\=")
			.replaceAll("[\r\n]+", "\\\\n");
	}
}
