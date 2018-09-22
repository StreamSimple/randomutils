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

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class UniformListSamplerTest
{
  @Test
  public void stressTest()
  {
    stressTestMultiHelper(100, 50, 10);
  }

  private void stressTestMultiHelper(int testCount, int listSize, int sampleSize)
  {
    for (int counter = 0; counter < testCount; counter++) {
      stressTestHelper(listSize, sampleSize);
    }
  }

  private void stressTestHelper(int listSize, int sampleSize)
  {
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
