package org.axonframework.queryhandling.responsetypes;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import static org.axonframework.common.ReflectionUtils.methodOf;
import static org.junit.Assert.*;

/**
 * Helper test implementation of {@link org.axonframework.queryhandling.responsetypes.ResponseType} tests.
 *
 * @param <R> a generic for the expected response type of the
 *            {@link org.axonframework.queryhandling.responsetypes.ResponseType} test subject
 */
public abstract class AbstractResponseTypeTest<R> {

    static final Boolean MATCHES = Boolean.TRUE;
    static final Boolean DOES_NOT_MATCHES = Boolean.FALSE;

    private final ResponseType<R> testSubject;

    protected AbstractResponseTypeTest(ResponseType<R> testSubject) {
        this.testSubject = testSubject;
    }

    /**
     * Helper function to make testing of the
     * {@link org.axonframework.queryhandling.responsetypes.ResponseType#matches(Type)} function easier.
     * Takes a {@code methodNameToTest} which it uses to pull a {@link java.lang.reflect.Method} from this abstract
     * class. There after it will pull the return {@link java.lang.reflect.Type} from that method, which it will use as
     * input for the test subject's match function.
     *
     * @param methodNameToTest a {@link java.lang.String} representing the function you want to extract a return type
     *                         from
     * @param expectedResult   a {@link java.lang.Boolean} which is the expected result of the matches call
     * @throws NoSuchMethodException if no {@link java.lang.reflect.Method} can be found for the given
     *                               {@code methodNameToTest}
     */
    protected void testMatches(String methodNameToTest, Boolean expectedResult) throws NoSuchMethodException {
        Method methodToTest = methodOf(getClass(), methodNameToTest);
        Type methodReturnType = methodToTest.getGenericReturnType();
        assertEquals(expectedResult, testSubject.matches(methodReturnType));
    }

    @SuppressWarnings("unused")
    public QueryResponse someQuery() {
        return new QueryResponse();
    }

    @SuppressWarnings("unused")
    public SubTypedQueryResponse someSubTypedQuery() {
        return new SubTypedQueryResponse();
    }

    @SuppressWarnings("unused")
    public Object someSuperTypedQuery() {
        return new Object();
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <E> E someUnboundedGenericQuery() {
        return (E) new SubTypedQueryResponse();
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <E extends QueryResponse> E someBoundedGenericQuery() {
        return (E) new SubTypedQueryResponse();
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <E extends SubTypedQueryResponse & QueryResponseInterface> E someMultiBoundedGenericQuery() {
        return (E) new ComplexTypedQueryResponse();
    }

    @SuppressWarnings({"unchecked", "unused"})
    public <E extends QueryResponseInterface> E someNonMatchingBoundedGenericQuery() {
        return (E) new QueryResponseInterface() {
        };
    }

    @SuppressWarnings("unused")
    public QueryResponse[] someArrayQuery() {
        return new QueryResponse[]{};
    }

    @SuppressWarnings("unused")
    public SubTypedQueryResponse[] someSubTypedArrayQuery() {
        return new SubTypedQueryResponse[]{};
    }

    @SuppressWarnings("unused")
    public Object[] someSuperTypedArrayQuery() {
        return new Object[]{};
    }

    @SuppressWarnings({"unused", "unchecked"})
    public <E> E[] someUnboundedGenericArrayQuery() {
        return (E[]) new SubTypedQueryResponse[]{};
    }

    @SuppressWarnings({"unused", "unchecked"})
    public <E extends QueryResponse> E[] someBoundedGenericArrayQuery() {
        return (E[]) new SubTypedQueryResponse[]{};
    }

    @SuppressWarnings({"unused", "unchecked"})
    public <E extends SubTypedQueryResponse & QueryResponseInterface> E[] someMultiBoundedGenericArrayQuery() {
        return (E[]) new ComplexTypedQueryResponse[]{};
    }

    @SuppressWarnings({"unused", "unchecked"})
    public <E extends QueryResponseInterface> E[] someNonMatchingBoundedGenericArrayQuery() {
        return (E[]) new SubTypedQueryResponse[]{};
    }

    @SuppressWarnings("unused")
    public List<QueryResponse> someListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public List<SubTypedQueryResponse> someSubListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public List<Object> someSuperListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E extends QueryResponse> List<E> someBoundedGenericListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E> List<E> someUnboundedGenericListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E extends SubTypedQueryResponse & QueryResponseInterface> List<E> someMultiBoundedGenericListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E extends QueryResponseInterface> List<E> someNonMatchingBoundedGenericListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public List<?> someUnboundedWildcardListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public List<? super QueryResponse> someLowerBoundedWildcardListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public List<? extends SubTypedQueryResponse> someUpperBoundedWildcardListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public List<? extends QueryResponseInterface> someNonMatchingUpperBoundedWildcardQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E> List<? extends E> someUnboundedGenericUpperBoundedWildcardListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E extends SubTypedQueryResponse> List<? extends E> someGenericUpperBoundedWildcardListQuery() {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public <E extends SubTypedQueryResponse & QueryResponseInterface> List<? extends E> someMultiGenericUpperBoundedWildcardListQuery
            () {
        return new ArrayList<>();
    }

    @SuppressWarnings("unused")
    public Set<QueryResponse> someSetQuery() {
        return new HashSet<>();
    }

    @SuppressWarnings("unused")
    public Stream<QueryResponse> someStreamQuery() {
        return Stream.of(new QueryResponse());
    }

    @SuppressWarnings("unused")
    public Map<QueryResponse, QueryResponse> someMapQuery() {
        return new HashMap<>();
    }

    static class QueryResponse {

    }

    static class SubTypedQueryResponse extends QueryResponse {

    }

    interface QueryResponseInterface {

    }

    static class ComplexTypedQueryResponse extends SubTypedQueryResponse implements QueryResponseInterface {

    }
}