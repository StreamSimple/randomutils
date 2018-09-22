/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsimple.randomutils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class UniformListSampler
{
  private Random rand;

  public UniformListSampler()
  {
    rand = new Random();
  }

  public UniformListSampler(final Random rand)
  {
    this.rand = rand;
  }

  public int[] sample(final int listSize, final int sampleSize)
  {
    if (listSize <= 0) {
      throw new IllegalArgumentException("The listSize must be positive.");
    }

    if (sampleSize <= 0) {
      throw new IllegalArgumentException("The sampleSize must be positive.");
    }

    if (listSize < sampleSize) {
      throw new IllegalArgumentException("The listSize is less than the sampleSize.");
    }

    if (listSize == sampleSize) {
      throw new IllegalArgumentException("The listSize equals the sampleSize.");
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

  public <T> List<T> sampleList(List<T> list, final int sampleSize)
  {
    int[] sampleIndices = sample(list.size(), sampleSize);
    List<T> sample = new ArrayList<>(sampleSize);

    for (int sampleIndex: sampleIndices) {
      sample.add(list.get(sampleIndex));
    }

    return sample;
  }
}
