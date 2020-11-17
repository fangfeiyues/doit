package com.fang.doit.algo.assignment.wordnet;

/**
 * @author by Feiyue on 2019/12/17 8:44 PM
 */
public class Outcast {

    private final WordNet wordNet;

    public Outcast(WordNet wordnet) {
        this.wordNet = wordnet;
    }


    public String outcast(String[] nouns) {
        int[] distance = new int[nouns.length];
        for (int i = 0; i < distance.length; i++) {
            String nouni = nouns[i];
            for (int j = 0; j < distance.length; j++)
                if (i != j)
                    distance[i] = distance[i] + wordNet.distance(nouni, nouns[j]);
        }

        int maxDistance = distance[0];
        int maxID = 0;
        for (int i = 0; i < distance.length; i++) {
            if (maxDistance < distance[i]) {
                maxDistance = distance[i];
                maxID = i;
            }
        }

        return nouns[maxID];
    }

    // see test client below
    public static void main(String[] args) {
        //
    }

}
