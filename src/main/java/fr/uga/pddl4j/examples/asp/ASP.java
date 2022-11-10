package fr.uga.pddl4j.examples.asp;

import fr.uga.pddl4j.parser.DefaultParsedProblem;
import fr.uga.pddl4j.plan.Plan;
import fr.uga.pddl4j.planners.AbstractPlanner;
import fr.uga.pddl4j.planners.statespace.HSP;
import fr.uga.pddl4j.problem.*;
import fr.uga.pddl4j.problem.operator.Action;
import fr.uga.pddl4j.problem.operator.ConditionalEffect;
import fr.uga.pddl4j.util.BitVector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.sat4j.core.VecInt;
import org.sat4j.pb.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.*;
import picocli.CommandLine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The class is an example. It shows how to create a simple A* search planner able to
 * solve an ADL problem by choosing the heuristic to used and its weight.
 *
 * @author D. Pellier
 * @version 4.0 - 30.11.2021
 */
@CommandLine.Command(name = "ASP",
        version = "ASP 1.0",
        description = "Solves a specified planning problem using A* search strategy.",
        sortOptions = false,
        mixinStandardHelpOptions = true,
        headerHeading = "Usage:%n",
        synopsisHeading = "%n",
        descriptionHeading = "%nDescription:%n%n",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n")


public class ASP extends AbstractPlanner {

    /**
     * The class logger.
     */
    private static final Logger LOGGER = LogManager.getLogger(ASP.class.getName());
    /**
     * Instantiates the planning problem from a parsed problem.
     *
     * @param problem the problem to instantiate.
     * @return the instantiated planning problem or null if the problem cannot be instantiated.
     */
    @Override
    public Problem instantiate(DefaultParsedProblem problem) {
        final Problem pb = new DefaultProblem(problem);
        pb.instantiate();
        return pb;
    }

    /**
     * Search a solution plan to a specified domain and problem using A*.
     *
     * @param problem the problem to solve.
     * @return the plan found or null if no plan was found.
     */
    @Override

    public Plan solve(final Problem problem) {
        //on peut récupérer les actions du problème avec pb.getActions()


        //Ici, on travaille sur les fluents du problème.
        List<Fluent> problemFluents = problem.getFluents();
        //System.out.println("Voici les fluents du problème");
        //System.out.println(problemFluents);
        //System.out.println("Voici les symboles des fluents du problème");

        int numberOfFluents = problemFluents.size();

        //Ici, on travaille sur l'état initial du problème

        InitialState initialState = problem.getInitialState();
        BitVector initialStatePositiveFluents = initialState.getPositiveFluents();
        //BitVector initialStateNegativeFluents = initialState.getNegativeFluents();
        //System.out.println("Voici les fluents POSITIFS de l'état initial");
        //System.out.println(initialStatePositiveFluents);
        //System.out.println("Voici les fluents NEGATIFS de l'état initial");
        //System.out.println(initialStateNegativeFluents);
        //System.out.println(initialState.toString());


        int[] initialStateFluentsSign = new int[numberOfFluents];

        for(int i=0; i<initialStateFluentsSign.length; i++){
            initialStateFluentsSign[i] = -(i+1);
        }


        int[] initialPosFluents = initialStatePositiveFluents.stream().toArray();
        for(int i=0; i<initialPosFluents.length ;i++){
            initialStateFluentsSign[initialPosFluents[i]] = - initialStateFluentsSign[initialPosFluents[i]];
        }



        //Ici, on travaille sur le goal
        Goal goalState = (Goal) problem.getGoal();

        BitVector goalStatePositiveFluents = goalState.getPositiveFluents();

        int[] goalStateFluentsSign = new int[numberOfFluents];

        for(int i=0; i<goalStateFluentsSign.length ;i++){
            goalStateFluentsSign[i] = -(i+1);
        }

        int[] goalPosFluents = goalStatePositiveFluents.stream().toArray();
        for(int i=0; i<goalPosFluents.length; i++){
            goalStateFluentsSign[goalPosFluents[i]] = -goalStateFluentsSign[goalPosFluents[i]];
        }


        //Ici, on travaille sur les actions
        List<Action> problemActions = problem.getActions();

        //on crée un vecteur contenant pour chaque action ses préconditions, ses effets positifs et négatifs
        int[][][] vectorActions = new int[problemActions.size()][3][];
        int[][] pivot = new int[3][];

        int numberOfAction = 0;
        for(Action problemAction: problemActions){
            System.out.println("Voici le nom de l'action n° "+numberOfAction+ " : "+problemAction.getName());

            //System.out.println("Voici les préconditions de cette action :");
            pivot[0] = problemAction.getPrecondition().getPositiveFluents().stream().toArray();
            //System.out.println("Voici les effets positifs");
            pivot[1] = problemAction.getConditionalEffects().get(0).getEffect().getPositiveFluents().stream().toArray();
            //System.out.println("Voici les effets négatifs");
            pivot[2] = problemAction.getConditionalEffects().get(0).getEffect().getNegativeFluents().stream().toArray();

            vectorActions[numberOfAction][0] = pivot[0];
            vectorActions[numberOfAction][1] = pivot[1];

            //on rajoute un signe - devant les effets négatifs
            for(int j =0; j<pivot[0].length;j++){
                pivot[0][j] = pivot[0][j]+1;
            }
            for(int j =0; j<pivot[1].length;j++){
                pivot[1][j] = pivot[1][j]+1;
            }
            for(int j =0; j<pivot[2].length;j++){
                pivot[2][j] = -(pivot[2][j]+1);
            }
            vectorActions[numberOfAction][2] = pivot[2];
            numberOfAction++;
        }


        //on calcule n, ie le nombre maximum d'étapes du plan
        double n = 0;
        int D = problem.getConstantSymbols().size();
        int Ap = 0;
        for(Fluent flu : problemFluents ){
            if(Ap < flu.getArguments().length){
                Ap = flu.getArguments().length;
                System.out.println(Ap);
            }
        }

        //n = Math.pow(2,Math.pow(D,Ap));
        //finalement, c'est infini pour notre exemple (sokoban). On fixe sa valeur à 500000



        final int maxVar = 1000000;
        final int nbClauses = numberOfAction;

        ISolver solver = SolverFactory.newDefault();

        //on ajoute la clause associée à l'état initial et à l'état but
        try {
            solver.addClause(new VecInt(initialStateFluentsSign)); // adapt Array to IVecInt
            solver.addClause(new VecInt(goalStateFluentsSign));
        } catch (ContradictionException e) {
            throw new RuntimeException(e);
        }


        // prepare the solver to accept MAXVAR variables. MANDATORY for MAXSAT solving
        solver.newVar(maxVar);
        solver.setExpectedNumberOfClauses(nbClauses);
        // Feed the solver using Dimacs format, using arrays of int
        // (best option to avoid dependencies on SAT4J IVecInt)
        for (int i=0;i<nbClauses;i++) {
            //ici, on crée la clause qui va bien
            //pour modéliser une possibilité d'action (ie une transition possible), on va créer les clauses
            //on concatène les (préconditions)+, les (effets négatifs)+, les (effets positifs)-
            //explication 1: les préconditions sont nécéssaires aux actions -> les entiers associés aux fluents sont positifs
            //explication :
            int [] clause = initialStateFluentsSign;
            try {
                solver.addClause(new VecInt(clause)); // adapt Array to IVecInt
            } catch (ContradictionException e) {
                throw new RuntimeException(e);
            }
        }
    int t = 234;



        return null;
    }

    /**
     * The main method of the <code>ASP</code> planner.
     *
     * @param args the arguments of the command line.
     */
    public static void main(String[] args) {
        try {
            final ASP planner = new ASP(); //on instancie notre planner
            CommandLine cmd = new CommandLine(planner);
            cmd.execute("domain.pddl","sokoban.pddl");
        } catch (IllegalArgumentException e) {
            LOGGER.fatal(e.getMessage());
        }
    }
}

