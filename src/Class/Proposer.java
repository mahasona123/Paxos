package Class;
import java.util.HashMap;
import java.util.Map;

public class Proposer {
    private int id;
    private int proposalNumber;
    private int proposedValue;
    private Map<Integer, Boolean> promises;
    private Map<Integer, Integer> acceptedValues;

    public Proposer(int id, int proposedValue) {
        this.id = id;
        this.proposalNumber = 0;
        this.proposedValue = proposedValue;
        this.promises = new HashMap<>();
        this.acceptedValues = new HashMap<>();
    }

    public void propose() {
        proposalNumber++;
        // Send prepare request to all acceptors
        for (Acceptor acceptor : Network.getAcceptors()) {
            acceptor.receivePrepare(id, proposalNumber);
        }
    }

    public void receivePromise(int acceptorId, int promiseProposalNumber, int lastAcceptedProposal, int lastAcceptedValue) {
        if (promiseProposalNumber == proposalNumber) {
            promises.put(acceptorId, true);
            if (lastAcceptedProposal != -1) {
                acceptedValues.put(lastAcceptedProposal, lastAcceptedValue);
            }
            if (promises.size() > Network.getAcceptors().size() / 2) {
                // Majority has promised, send accept request with the lowest value
                int lowestValue = proposedValue;
                if (!acceptedValues.isEmpty()) {
                    lowestValue = Math.min(lowestValue, acceptedValues.values().stream().min(Integer::compareTo).orElse(proposedValue));
                }
                for (Acceptor acceptor : Network.getAcceptors()) {
                    acceptor.receiveAccept(id, proposalNumber, lowestValue);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}


