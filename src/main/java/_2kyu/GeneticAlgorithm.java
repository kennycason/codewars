package _2kyu;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.ToDoubleFunction;

public class GeneticAlgorithm {
    private static final Random RANDOM = new Random();

    private String generate(final int length) {
        final StringBuilder chromosome = new StringBuilder();
        for (int i = 0; i < length; i++) {
            chromosome.append(RANDOM.nextBoolean() ? "1" : "0");
        }
        return chromosome.toString();
    }

    private List<String> generatePopulation(final int size, final int length) {
        final List<String> population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            population.add(generate(length));
        }
        return population;
    }

    private void refreshPopulation(final List<String> population,
                                                final int size,
                                                final double p_c,
                                                final double p_m) {
        while (population.size() < size) {
            if (RANDOM.nextDouble() > p_c) {
                final String[] crossover = crossover(population.get(0), population.get(1));
                population.add(crossover[0]);
                population.add(crossover[1]); // may go over size, but it's ok
            } else {
                population.add(mutate(population.get(RANDOM.nextInt(2)), p_m));
            }

        }
    }

    // fitness = 0 is wrong, fitness = 1 is right
    private String[] select(final List<String> population, final List<Double> fitnesses) {
        final List<String> selection = new ArrayList<>();
        for (int i = 0; i < population.size(); i++) {
            //System.out.println(">: " + population.get(i) + ": " + fitnesses.get(i));
        }
        while (selection.size() < 2) {
            for (int i = 0; i < population.size(); i++) {
                if (RANDOM.nextDouble() < fitnesses.get(i)) {
                    selection.add(population.get(i));
                    //System.out.println("select: " + population.get(i) + ": " + fitnesses.get(i));
                    break;
                }
            }
        }
        return selection.toArray(new String[2]);
    }

    private String mutate(final String chromosome, final double p) {
        final StringBuilder newChromosome = new StringBuilder();
        for (int i = 0; i < chromosome.length(); i++) {
            if (RANDOM.nextDouble() < p) {
                System.out.println("mutate");
                if (chromosome.charAt(i) == '1') {
                    newChromosome.append('0');
                } else {
                    newChromosome.append('1');
                }
            } else {
                newChromosome.append(chromosome.charAt(i));
            }
        }
        return newChromosome.toString();
    }

    private String[] crossover(final String chromosome1, final String chromosome2) {
        final int index = RANDOM.nextInt(chromosome1.length());
        final StringBuilder newChromosome1 = new StringBuilder();
        final StringBuilder newChromosome2 = new StringBuilder();
        // swap first segment
        for (int i = 0; i < index; i++) {
            newChromosome1.append(chromosome2.charAt(i));
            newChromosome2.append(chromosome1.charAt(i));
        }
        // copy second segment as-is
        for (int i = index; i < chromosome1.length(); i++) {
            newChromosome1.append(chromosome1.charAt(i));
            newChromosome2.append(chromosome2.charAt(i));
        }
        //System.out.println(chromosome1 + " -> " + newChromosome1);
        //System.out.println(chromosome2 + " -> " + newChromosome2);
        return new String[] {
                newChromosome1.toString(),
                newChromosome2.toString()
        };
    }

    private List<Double> calculateFitnesses(final List<String> populations, final ToDoubleFunction<String> fitness) {
        final List<Double> fitnesses = new ArrayList<>();
        for (final String chromosome : populations) {
            fitnesses.add(fitness.applyAsDouble(chromosome));
        }
        return fitnesses;
    }

    public String run(final ToDoubleFunction<String> fitness,
                      final int length,
                      final double p_c,
                      final double p_m) {
        return run(fitness, length, p_c, p_m, 100);
    }

    public String run(final ToDoubleFunction<String> fitness,
                      final int length,
                      final double p_c,
                      final double p_m,
                      final int iterations) {
        final List<String> population = generatePopulation(50, length);
        for (int i = 0; i < iterations; i++) {
            final List<Double> fitnesses = calculateFitnesses(population, fitness);
            final String[] mostFit = select(population, fitnesses);
            population.clear(); // kill the weaklings
            population.add(mostFit[0]); // rebuild population
            population.add(mostFit[1]);
            refreshPopulation(population, 50, p_c, p_m);

            System.out.println(mostFit[0] + ": " + fitness.applyAsDouble(mostFit[0]));
        }
        return population.get(0);
    }

    // score = sqrt((chromosome sum - ideal sum)^2+(chromosome product - ideal product)^2)
    // fitness = 1/(score + 1)
}