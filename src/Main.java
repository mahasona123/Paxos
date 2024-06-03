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
        Network.addProposer(proposer1);

        // Start the proposal process
        proposer1.propose();
    }
}
