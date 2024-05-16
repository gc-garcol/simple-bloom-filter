package gc.garcol.simplebloomfilter;

import java.util.function.Function;

public class BloomFilter<T> {

  private final boolean[] signatureVector;
  private final Function<T, Integer>[] hashFunctions;

  @SafeVarargs
  public BloomFilter(int size, Function<T, Integer>... hashFunctions) {
    this.signatureVector = new boolean[size];
    this.hashFunctions = hashFunctions;
  }

  public void add(T element) {
    for (Function<T, Integer> hashFunction : hashFunctions) {
      signatureVector[indexOf(hashFunction, element)] = true;
    }
  }

  public boolean maybeContain(T element) {
    for (Function<T, Integer> hashFunction : hashFunctions) {
      if (!signatureVector[indexOf(hashFunction, element)]) {
        return false;
      }
    }
    return true;
  }

  private Integer indexOf(Function<T, Integer> hashFunction, T element) {
    return (hashFunction.apply(element) % signatureVector.length + signatureVector.length)
        % signatureVector.length;
  }
}
