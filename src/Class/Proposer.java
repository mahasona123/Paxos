package Class;

import java.util.HashMap;
import java.util.Map;

public class Proposer {
    private int id;
    private int proposalNumber;
    private int proposedValue;
    private Map<Integer, Boolean> promises;

    public Proposer(int id, int proposedValue) {
        this.id = id;
        this.proposalNumber = 0;
        this.proposedValue = proposedValue;
        this.promises = new HashMap<>();
    }

    public void propose() {
        proposalNumber++;
        // Send prepare request to all acceptors
        for (Acceptor acceptor : Network.getAcceptors()) {
            acceptor.receivePrepare(id, proposalNumber);
        }
    }

    public void receivePromise(int acceptorId, int promiseProposalNumber) {
        if (promiseProposalNumber == proposalNumber) {
            promises.put(acceptorId, true);
            if (promises.size() > Network.getAcceptors().size() / 2) {
                // Majority has promised, send accept request
                for (Acceptor acceptor : Network.getAcceptors()) {
                    acceptor.receiveAccept(id, proposalNumber, proposedValue);
                }
            }
        }
    }

    public int getId() {
        return id;
    }
}

