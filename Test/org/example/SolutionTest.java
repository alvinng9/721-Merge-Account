package org.example;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolutionTest {

    @Test
    public void accountsMergeTest() {
        Solution solution = new Solution();
        List<List<String>> input = new ArrayList<>();
        input.add(new ArrayList<>(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com")));
        input.add(new ArrayList<>(Arrays.asList("John","johnsmith@mail.com","john00@mail.com")));
        input.add(new ArrayList<>(Arrays.asList("John","johnnybravo@mail.com")));
        input.add(new ArrayList<>(Arrays.asList("Mary","mary@mail.com")));
        List<List<String>> output = new ArrayList<>();
        output.add(new ArrayList<>(Arrays.asList("John","john00@mail.com",
                                                 "john_newyork@mail.com","johnsmith@mail.com")));
        output.add(new ArrayList<>(Arrays.asList("John","johnnybravo@mail.com")));
        output.add(new ArrayList<>(Arrays.asList("Mary","mary@mail.com")));
        Assert.assertEquals(output, solution.accountsMerge(input));


    }
  
}