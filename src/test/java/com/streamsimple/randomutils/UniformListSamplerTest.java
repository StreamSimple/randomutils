package com.streamsimple.randomutils;

import org.junit.Assert;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class UniformListSamplerTest
{
  @Test
  public void stressTest() {
    stressTestMultiHelper(100, 50, 10);
  }

  private void stressTestMultiHelper(int testCount, int listSize, int sampleSize) {
    for (int counter = 0; counter < testCount; counter++) {
      stressTestHelper(listSize, sampleSize);
    }
  }

  private void stressTestHelper(int listSize, int sampleSize) {
    final UniformListSampler listSampler = new UniformListSampler();
    final int[] sampleIndices = listSampler.sample(listSize, sampleSize);

    Set<Integer> indices = new HashSet<>();

    for (int sampleIndex: sampleIndices) {
      Assert.assertTrue(sampleIndex < listSize && sampleIndex >= 0);
      indices.add(sampleIndex);
    }

    Assert.assertEquals(sampleSize, indices.size());
  }
}
