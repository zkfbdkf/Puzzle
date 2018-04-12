import java.util.*;

public class Puzzle {
	// given states
	static int[][] Given1 = { { 1, 3, 2 }, { 0, 7, 8 }, { 5, 6, 4 } };
	static int[][] Given2 = { { 3, 7, 0 }, { 1, 6, 2 }, { 5, 4, 8 } };
	static int[][] Given3 = { { 7, 5, 6 }, { 3, 4, 2 }, { 1, 0, 8 } };
	// the answer
	static int[][] Correct = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
	static Queue<Object> Node = new LinkedList<Object>();
	static int node = 0;
	static int P;

	public static void main(String args[]) {
		boolean check = false;
		Node Answer;

		// initiate with Given state.
		Node start = new Node(Given3);
		start.prevmove = 5;
		Node.offer(start);
		node++;

		// start measuring time
		long startTime = System.currentTimeMillis();
		while (true) {
			Node Present = (Node) Node.poll();

			// check the answer
			check = Check(Present);

			if (!check) {
				GetNext(Present);// go to next move
			} else {
				Answer = Present;
				break;
			}
		}
		System.out.println("Correct!");
		long endTime = System.currentTimeMillis();
		// end measuring time
		long LTime = endTime - startTime;
		// show time
		System.out.println(LTime + "(ms)");

		// print the answer
		System.out.print("Move: ");
		for (int i = 0; i < Answer.prev.size(); i++) {
			if (Answer.prev.get(i) == 1)
				System.out.print("Right ");
			else if (Answer.prev.get(i) == 2)
				System.out.print("Left ");
			else if (Answer.prev.get(i) == 3)
				System.out.print("Down ");
			else if (Answer.prev.get(i) == 4)
				System.out.print("Up ");
		}
	}

	private static void print(Node present) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(present.present[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean Check(Node present) {
		int correct = 0, current = 0;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++) {
				correct = Correct[i][j];
				current = present.present[i][j];

				if (correct == current)
					continue;
				else
					return false;
			}
		return true;
	}

	private static void GetNext(Node present) {
		int x = 0, y = 0;
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (present.present[i][j] == 0) {// get the position of blank
													// space
					x = i;
					y = j;
				}

		if (x == 0) {
			if (present.prevmove != 3)// if the previous move was not move down
				MoveUP(present, x, y);
		}
		if (x == 1) {
			if (present.prevmove != 3)// if the previous move was not move down
				MoveUP(present, x, y);
			if (present.prevmove != 4)// if the previous move was not move up
				MoveDown(present, x, y);
		}
		if (x == 2) {
			if (present.prevmove != 4)// if the previous move was not move up
				MoveDown(present, x, y);
		}
		if (y == 0) {
			if (present.prevmove != 1)// if the previous move was not move right
				Moveleft(present, x, y);
		}
		if (y == 1) {
			if (present.prevmove != 1)// if the previous move was not move right
				Moveleft(present, x, y);
			if (present.prevmove != 2)// if the previous move was not move left
				MoveRight(present, x, y);
		}
		if (y == 2) {
			if (present.prevmove != 2)// if the previous move was not move left
				MoveRight(present, x, y);
		}
	}

	private static void MoveRight(Node present, int x, int y) {
		Node Nnode = new Node(present);

		int temp = Nnode.present[x][y - 1];
		// move
		Nnode.present[x][y] = temp;
		Nnode.present[x][y - 1] = 0;

		// add the move direction
		Nnode.prev.add(1);
		Nnode.prevmove = 1;

		// put into the queue
		Node.offer(Nnode);
		node++;
	}

	private static void Moveleft(Node present, int x, int y) {
		Node Nnode = new Node(present);

		int temp = Nnode.present[x][y + 1];
		// move
		Nnode.present[x][y] = temp;
		Nnode.present[x][y + 1] = 0;

		// add the move direction
		Nnode.prev.add(2);
		Nnode.prevmove = 2;
		// put into the queue
		Node.offer(Nnode);
		node++;
	}

	private static void MoveDown(Node present, int x, int y) {
		Node Nnode = new Node(present);

		int temp = Nnode.present[x - 1][y];
		// move
		Nnode.present[x][y] = temp;
		Nnode.present[x - 1][y] = 0;

		// add the move direction
		Nnode.prev.add(3);
		Nnode.prevmove = 3;

		// put into the queue
		Node.offer(Nnode);
		node++;
	}

	private static void MoveUP(Node present, int x, int y) {
		Node Nnode = new Node(present);

		int temp = Nnode.present[x + 1][y];
		// move
		Nnode.present[x][y] = temp;
		Nnode.present[x + 1][y] = 0;

		// add the move direction
		Nnode.prev.add(4);
		Nnode.prevmove = 4;

		// put into the queue
		Node.offer(Nnode);
		node++;
	}
}

class Node {
	int[][] present;
	int prevmove;
	ArrayList<Integer> prev;

	public Node(int[][] present) {
		prev = new ArrayList<Integer>();
		this.present = present;
	}

	public Node(Node Present) {
		present = new int[3][3];
		prev = new ArrayList<Integer>();
		this.prevmove = Present.prevmove;

		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				this.present[i][j] = Present.present[i][j];

		for (int i = 0; i < Present.prev.size(); i++) {
			this.prev.add(Present.prev.get(i));
		}
	}
}
