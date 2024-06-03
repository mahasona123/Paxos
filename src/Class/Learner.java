package Class;

import java.util.HashMap;
import java.util.Map;

public class Learner {
    private int id;
    private Map<Integer, Integer> acceptedValuesCount;
    private int learnedValue;

    public Learner(int id) {
        this.id = id;
        this.acceptedValuesCount = new HashMap<>();
    }

    public void receiveAccepted(int acceptorId, int proposalNumber, int value) {
        acceptedValuesCount.put(value, acceptedValuesCount.getOrDefault(value, 0) + 1);
        if (acceptedValuesCount.get(value) > Network.getAcceptors().size() / 2) {
            learnedValue = value;
            System.out.println("Learner " + id + " learned value: " + value);
        }
    }

    public int getLearnedValue() {
        return learnedValue;
    }
}
