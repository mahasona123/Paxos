package Class;
public class Acceptor {
    private int id;
    private int promisedProposalNumber;
    private int acceptedProposalNumber;
    private int acceptedValue;

    public Acceptor(int id) {
        this.id = id;
        this.promisedProposalNumber = -1;
        this.acceptedProposalNumber = -1;
        this.acceptedValue = Integer.MAX_VALUE; // Initialize to a high value
    }

    public void receivePrepare(int proposerId, int proposalNumber) {
        if (proposalNumber > promisedProposalNumber) {
            promisedProposalNumber = proposalNumber;
            // Send promise to proposer with last accepted proposal and value
            Network.getProposer(proposerId).receivePromise(id, proposalNumber, acceptedProposalNumber, acceptedValue);
        }
    }

    public void receiveAccept(int proposerId, int proposalNumber, int value) {
        if (proposalNumber >= promisedProposalNumber) {
            promisedProposalNumber = proposalNumber;
            acceptedProposalNumber = proposalNumber;
            acceptedValue = value;
            // Notify learners
            for (Learner learner : Network.getLearners()) {
                learner.receiveAccepted(id, proposalNumber, value);
            }
        }
    }
}


