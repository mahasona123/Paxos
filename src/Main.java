import Class.*;

public class Main {
    public static void main(String[] args) {
        // Create and add acceptors
        for (int i = 1; i <= 5; i++) {
            Network.addAcceptor(new Acceptor(i));
        }

        // Create and add learners
        for (int i = 1; i <= 3; i++) {
            Network.addLearner(new Learner(i));
        }

        // Create and add proposers
        Proposer proposer1 = new Proposer(1, 42);
        Proposer proposer2 = new Proposer(2, 24);
        Proposer proposer3 = new Proposer(3, 36);
        Network.addProposer(proposer1);
        Network.addProposer(proposer2);
        Network.addProposer(proposer3);

        // Start the proposal process
        proposer1.propose();
        proposer2.propose();
        proposer3.propose();
    }
}
