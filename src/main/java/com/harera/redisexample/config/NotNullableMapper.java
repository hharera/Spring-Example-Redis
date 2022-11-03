package com.harera.redisexample.config;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.AbstractConverter;
import org.modelmapper.AbstractProvider;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.convention.MatchingStrategies;

import static com.harera.redisexample.config.DateFormat.DATE_FORMAT;
import static java.time.LocalDate.parse;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

public class NotNullableMapper extends ModelMapper {

    public NotNullableMapper() {
        this.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Provider<LocalDate> localDateProvider = createLocalDateProvider();
        Converter<String, LocalDate> localDateConvertor = createLocalDateConverter();
        this.createTypeMap(String.class, LocalDate.class);
        this.addConverter(localDateConvertor);
        this.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        Provider<LocalDateTime> localDateTimeProvider = createLocalDateTimeProvider();
        Converter<String, LocalDateTime> localDateTimeConvertor =
                createLocalDateTimeConverter();
        this.createTypeMap(String.class, LocalDateTime.class);
        this.addConverter(localDateTimeConvertor);
        this.getTypeMap(String.class, LocalDateTime.class)
                .setProvider(localDateTimeProvider);
    }

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return super.map(source, destinationType);
    }

    private Provider<LocalDate> createLocalDateProvider() {
        return new AbstractProvider<LocalDate>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };
    }

    private Provider<LocalDateTime> createLocalDateTimeProvider() {
        return new AbstractProvider<LocalDateTime>() {
            @Override
            public LocalDateTime get() {
                return LocalDateTime.now();
            }
        };
    }

    private Converter<String, LocalDate> createLocalDateConverter() {
        return new AbstractConverter<String, LocalDate>() {
            @Override
            protected LocalDate convert(String source) {
                if (isNotBlank(source)) {
                    DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT);
                    return parse(source, format);
                }
                return null;
            }
        };
    }

    private Converter<String, LocalDateTime> createLocalDateTimeConverter() {
        return new AbstractConverter<String, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(String source) {
                if (isNotBlank(source)) {
                    return LocalDateTime.parse(source, ISO_LOCAL_DATE_TIME);
                }
                return null;
            }
        };

    }
}
