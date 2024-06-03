package Class;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private static List<Proposer> proposers = new ArrayList<>();
    private static List<Acceptor> acceptors = new ArrayList<>();
    private static List<Learner> learners = new ArrayList<>();

    public static void addProposer(Proposer proposer) {
        proposers.add(proposer);
    }

    public static void addAcceptor(Acceptor acceptor) {
        acceptors.add(acceptor);
    }

    public static void addLearner(Learner learner) {
        learners.add(learner);
    }

    public static List<Proposer> getProposers() {
        return proposers;
    }

    public static List<Acceptor> getAcceptors() {
        return acceptors;
    }

    public static List<Learner> getLearners() {
        return learners;
    }

    public static Proposer getProposer(int id) {
        return proposers.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }
}
