package com.streamsimple.randomutils;

import java.util.Arrays;
import java.util.Random;

public class UniformListSampler
{
  private Random rand;

  public UniformListSampler()
  {
    rand = new Random();
  }

  public UniformListSampler(final Random rand) {
    this.rand = rand;
  }

  public int[] sample(final int listSize, final int sampleSize) {
    if (listSize <= 0) {
      throw new IllegalArgumentException("The listSize must be positive.");
    }

    if (sampleSize <= 0) {
      throw new IllegalArgumentException("The sampleSize must be positive.");
    }

    final int[] sampleIndices = new int[sampleSize];

    for (int currentIndexCount = 0; currentIndexCount < sampleSize;) {
      int nextSampleIndex = rand.nextInt(listSize - currentIndexCount);

      for (int tagIndexIndex = 0; tagIndexIndex < currentIndexCount; tagIndexIndex++) {
        int tagIndex = sampleIndices[tagIndexIndex];

        if (nextSampleIndex >= tagIndex) {
          nextSampleIndex++;
        }
      }

      sampleIndices[currentIndexCount] = nextSampleIndex;
      currentIndexCount++;
      Arrays.sort(sampleIndices, 0, currentIndexCount);
    }

    return sampleIndices;
  }
}
