package org.atlasapi.persistence.logging;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTime;

import com.google.common.base.Functions;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.metabroadcast.common.time.DateTimeZones;

public final class AdapterLogEntry {

	private final DateTime timestamp;
	private final String id;
	private final Severity severity;
	
	private String uri;
	private ExceptionSummary e;
	private String description;
	private String sourceClassName;
	
	public static AdapterLogEntry errorEntry() {
	    return new AdapterLogEntry(Severity.ERROR);
	}
	public static AdapterLogEntry warnEntry() {
	    return new AdapterLogEntry(Severity.WARN);
	}
	public static AdapterLogEntry infoEntry() {
	    return new AdapterLogEntry(Severity.INFO);
	}
	public static AdapterLogEntry debugEntry() {
	    return new AdapterLogEntry(Severity.DEBUG);
	}
	
	public enum Severity {
		ERROR,
		WARN,
		INFO,
		DEBUG;

		public boolean isMoreSevereOrSameAs(Severity severity) {
			return this.ordinal() <= severity.ordinal();
		}
	}
	
	public AdapterLogEntry(String id, Severity severity, DateTime timestamp) {
		this.severity = severity;
		this.id = checkNotNull(id);
		this.timestamp = checkNotNull(timestamp);
	}
	
	public AdapterLogEntry(Severity severity) {
		this(UUID.randomUUID().toString(), severity, new DateTime(DateTimeZones.UTC));
	}
	
	public AdapterLogEntry withUri(String uri) {
		this.uri = uri;
		return this;
	}
	
	public AdapterLogEntry withCause(Exception e) {
		return withException(new ExceptionSummary(e));
	}
	
	public AdapterLogEntry withException(ExceptionSummary exceptionSummary) {
		this.e = exceptionSummary;
		return this;
	}

	public AdapterLogEntry withDescription(String message) {
		this.description = message;
		return this;
	}
	
   public AdapterLogEntry withDescription(String formatString, Object...args) {
        return withDescription(String.format(formatString, args));
    }
	
	public String description() {
		return description;
	}
	
	public String uri() {
		return uri;
	}
	
	public DateTime timestamp() {
		return timestamp;
	}

	public AdapterLogEntry withSource(Class<?> clazz) {
		return withSourceClassName(clazz.getName());
	}
	
	public AdapterLogEntry withSourceClassName(String sourceClassName) {
		this.sourceClassName = sourceClassName;
		return this;
	}
	
	public String id() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AdapterLogEntry) {
			return id.equals(((AdapterLogEntry) obj).id);
		}
		return false;
	}
	
	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", id).toString();
	}

	public static class ExceptionSummary {
		
		private final String clazz;
		private final String message;
		private final List<String> trace;
		private final ExceptionSummary cause;
		
		public ExceptionSummary(Throwable e) {
			this(e.getClass().getName(), e.getMessage(), toStrings(e.getStackTrace()), toCause(e));
		}	

		private static ExceptionSummary toCause(Throwable e) {
			Throwable cause = e.getCause();
			if (cause != null) {
				return new ExceptionSummary(cause);
			}
			return null;
		}

		private static List<String> toStrings(StackTraceElement[] stackTrace) {
			return Lists.transform(ImmutableList.copyOf(stackTrace), Functions.toStringFunction());
		}

		public ExceptionSummary(String clazz, String message, List<String> trace, ExceptionSummary cause) {
			this.trace = trace;
			this.clazz = checkNotNull(clazz);
			this.message = message;
			this.cause = cause;
		}
		
		public String className() {
			return clazz;
		}
		
		public String message() {
			return message;
		}
		
		@Override
		public String toString() {
			return classAndMessage();
		}

		public Collection<String> trace() {
			return trace;
		}
		
		public List<String> traceAndMessage() {
			return ImmutableList.copyOf(Iterables.concat(ImmutableList.of(classAndMessage()), trace));
		}

		private String classAndMessage() {
			return clazz + (message == null ? "" : " :" +  message);
		}

		public ExceptionSummary cause() {
			return cause;
		}

		public List<String> fullTrace() {
			List<String> fullTrace = Lists.newArrayList();
			for (ExceptionSummary summary : exceptionChain()) {
				fullTrace.addAll(summary.traceAndMessage());
			}
			return fullTrace;
		}
		
		private List<ExceptionSummary> exceptionChain() {
			List<ExceptionSummary> chain = Lists.newArrayList();
			ExceptionSummary current = this;
			while (current != null) {
				chain.add(current);
				current = current.cause;
			}
			return chain;
		}
	} 

	public ExceptionSummary exceptionSummary() {
		return e;
	}

	public String classNameOfSource() {
		return sourceClassName;
	}

	public Severity severity() {
		return severity;
	}
}
