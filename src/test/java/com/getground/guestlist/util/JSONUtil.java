package com.getground.guestlist.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;
import static com.fasterxml.jackson.databind.type.TypeFactory.defaultInstance;

/**
 * Class responsible for manipulating the data related to a JSON object.
 */
public final class JSONUtil {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setSerializationInclusion(NON_EMPTY);
        MAPPER.setVisibility(MAPPER.getVisibilityChecker().withFieldVisibility(ANY));
        MAPPER.registerModule(new JavaTimeModule());
    }

    private JSONUtil() {
    }

    /**
     * Read a certain byte array and map its value to a certain bean type.
     *
     * @param content The byte array to be converted.
     * @param type The {@link JavaType} used for the conversion.
     * @return The representation of the content as a bean.
     */
    public static <T> T byteArrayToBean(final byte[] content, final Class<T> type) {
        try {
            return MAPPER.readValue(content, defaultInstance().constructType(type));
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     * Read a certain byte array and map its value to a certain bean type.
     *
     * @param content The byte array to be converted.
     * @param type The {@link JavaType} used for the conversion.
     * @return The representation of the content as a bean.
     */
    public static <T> T byteArrayToBean(final byte[] content, final TypeReference<T> type) {
        try {
            return MAPPER.readValue(content, defaultInstance().constructType(type));
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     * Read a certain JSON file and map its value to a certain bean type.
     *
     * @param path The of the file to be read.
     * @param type The {@link JavaType} used for the conversion.
     * @return The representation of the file content as a bean.
     */
    public static <T> T fileToBean(final String path, final Class<T> type) {
        try {
            final var file = new ClassPathResource(path).getFile();
            return MAPPER.readValue(file, defaultInstance().constructType(type));
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     * Read a certain JSON file and map its value to a certain bean type.
     *
     * @param path The of the file to be read.
     * @param type The {@link TypeReference} used for the conversion.
     * @return The representation of the file content as a bean.
     */
    public static <T> T fileToBean(final String path, final TypeReference<T> type) {
        try {
            final var file = new ClassPathResource(path).getFile();
            return MAPPER.readValue(file, defaultInstance().constructType(type));
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     * @see JSONUtil#beanToString(Object).
     */
    public static <T> String beanToString(final String path, final Class<T> type) {
        try {
            final var bean = fileToBean(path, type);
            return MAPPER.writeValueAsString(bean);
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }

    /**
     * Transform a certain bean into a JSON string representation.
     *
     * @param bean The bean to be converted into string.
     * @return The bean as string.
     */
    private static <T> String beanToString(final T bean) {
        try {
            return MAPPER.writeValueAsString(bean);
        } catch (final IOException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
