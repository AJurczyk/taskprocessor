package com.ajurczyk.processor.domain.service;

import com.ajurczyk.processor.domain.Task;
import com.ajurczyk.processor.domain.TaskResult;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static com.ajurczyk.processor.domain.Status.DONE;
import static com.ajurczyk.processor.domain.Status.IN_PROGRESS;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BestMatchServiceTest {

    private static final UUID SOME_UUID = UUID.fromString("4591ae89-606f-4076-baf6-8822282e076e");

    @ParameterizedTest
    @MethodSource("testDataProvider")
    void shouldFindBestMatches(Task input, Task expected) {
        // given
        BestMatchService bestMatchService = new BestMatchService();

        // when
        bestMatchService.match(input);

        // then
        assertEquals(expected, input);
    }

    static Stream<Arguments> testDataProvider() {
        return Stream.of(
                        Arguments.of(Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("bcd")
                                                        .input("abcd")
                                                        .status(IN_PROGRESS)
                                                        .build(),
                                        Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("bcd")
                                                        .input("abcd")
                                                        .progress(100)
                                                        .status(DONE)
                                                        .result(new TaskResult("bcd", 1, 0))
                                                        .build()),
                        Arguments.of(Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("bwd")
                                                        .input("abcd")
                                                        .status(IN_PROGRESS)
                                                        .build(),
                                        Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("bwd")
                                                        .input("abcd")
                                                        .progress(100)
                                                        .status(DONE)
                                                        .result(new TaskResult("bcd", 1, 1))
                                                        .build()),
                        Arguments.of(Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("cfg")
                                                        .input("abcdefg")
                                                        .status(IN_PROGRESS)
                                                        .build(),
                                        Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("cfg")
                                                        .input("abcdefg")
                                                        .progress(100)
                                                        .status(DONE)
                                                        .result(new TaskResult("efg", 4, 1))
                                                        .build()),
                        Arguments.of(Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("abc")
                                                        .input("abcabc")
                                                        .status(IN_PROGRESS)
                                                        .build(),
                                        Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("abc")
                                                        .input("abcabc")
                                                        .progress(100)
                                                        .status(DONE)
                                                        .result(new TaskResult("abc", 0, 0))
                                                        .build()),
                        Arguments.of(Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("tdd")
                                                        .input("abcdefg")
                                                        .status(IN_PROGRESS)
                                                        .build(),
                                        Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("tdd")
                                                        .input("abcdefg")
                                                        .progress(100)
                                                        .status(DONE)
                                                        .result(new TaskResult("bcd", 1, 2))
                                                        .build()),
                        Arguments.of(Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("xyz")
                                                        .input("abcdefg")
                                                        .status(IN_PROGRESS)
                                                        .build(),
                                        Task.builder()
                                                        .uuid(SOME_UUID)
                                                        .pattern("xyz")
                                                        .input("abcdefg")
                                                        .progress(100)
                                                        .status(DONE)
                                                        .build())

        );
    }
}

