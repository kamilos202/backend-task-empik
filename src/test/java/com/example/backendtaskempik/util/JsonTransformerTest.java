package com.example.backendtaskempik.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
public class JsonTransformerTest {

    @Autowired
    private JsonTransformer jsonTransformer;

    @ParameterizedTest
    @MethodSource("dataForDeserialization")
    <T> void shouldDeserializeWithoutAnyExceptions(String object, Class<T> classType, T expectedOutput) {
        // when
        Optional<T> result = jsonTransformer.deserialize(object, classType);

        // then
        assertThat(result)
                .isNotEmpty()
                .hasValue(expectedOutput);
    }

    private static Stream<Arguments> dataForDeserialization() {
        return Stream.of(
                arguments(
                        "\"2222-12-12\"",
                        LocalDate.class,
                        LocalDate.of(2222, Month.DECEMBER, 12)
                ),
                arguments("{\"s1\":\"text\",\"k1\":1}",
                        Foo.class,
                        new Foo("text", 1)
                ),
                arguments("null",
                        Optional.class,
                        Optional.empty()
                ),
                arguments("\"str\"",
                        String.class,
                        "str"
                )
        );
    }

    private record Foo(String s1, int k1) {
    }
}
