package org.micha.apps.adventofcode2022.days;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

public class DayTwo extends AdventDayRunner<Integer, Integer> {

	protected DayTwo(String fileName, String identifierOfDay) throws AdventOfCodeException {
		super(fileName, identifierOfDay);
	}

	@Override
	public Integer partOne() throws AdventOfCodeException {
		return this.getData().stream().map(RockPaperScissorRound::new).mapToInt(RockPaperScissorRound::getScore).sum();
	}

	@Override
	public Integer partTwo() throws AdventOfCodeException {
		return this.getData().stream().map(RockPaperScissorRoundTwo::new).mapToInt(RockPaperScissorRoundTwo::getScore).sum();
	}

	protected static class RockPaperScissorRound {

		private static final String THEIR_ROCK = "A";
		private static final String THEIR_PAPER = "B";
		private static final String THEIR_SCISSOR = "C";
		private static final String MY_ROCK = "X";
		private static final String MY_PAPER = "Y";
		private static final String MY_SCISSOR = "Z";
		private static final int ROCK_VALUE = 1;
		private static final int PAPER_VALUE = 2;
		private static final int SCISSOR_VALUE = 3;
		private static final int WIN_VALUE = 6;
		private static final int DRAW_VALUE = 3;
		private static final int LOSE_VALUE = 0;

		private final String theirs;
		private final String mine;

		protected RockPaperScissorRound(String line) {
			this.theirs = String.valueOf(line.charAt(0));
			this.mine = String.valueOf(line.charAt(2));
		}

		protected int getScore() {
			if (mine.equals(MY_ROCK))
				return scoreIfMineIsRock();
			if (mine.equals(MY_PAPER))
				return scoreIfMineIsPaper();
			return scoreIfMineIsScissor();
		}

		private int scoreIfMineIsRock() {
			int baseScore = ROCK_VALUE;
			if (theirs.equals(THEIR_ROCK))
				baseScore += DRAW_VALUE;
			if (theirs.equals(THEIR_PAPER))
				baseScore += LOSE_VALUE;
			if (theirs.equals(THEIR_SCISSOR))
				baseScore += WIN_VALUE;
			return baseScore;
		}

		private int scoreIfMineIsPaper() {
			int baseScore = PAPER_VALUE;
			if (theirs.equals(THEIR_ROCK))
				baseScore += WIN_VALUE;
			if (theirs.equals(THEIR_PAPER))
				baseScore += DRAW_VALUE;
			if (theirs.equals(THEIR_SCISSOR))
				baseScore += LOSE_VALUE;
			return baseScore;
		}

		private int scoreIfMineIsScissor() {
			int baseScore = SCISSOR_VALUE;
			if (theirs.equals(THEIR_ROCK))
				baseScore += LOSE_VALUE;
			if (theirs.equals(THEIR_PAPER))
				baseScore += WIN_VALUE;
			if (theirs.equals(THEIR_SCISSOR))
				baseScore += DRAW_VALUE;
			return baseScore;
		}
	}

	protected static class RockPaperScissorRoundTwo {

		private static final String THEIR_ROCK = "A";
		private static final String THEIR_PAPER = "B";
		private static final String THEIR_SCISSOR = "C";
		private static final String NEED_TO_LOSE = "X";
		private static final String NEED_TO_DRAW = "Y";
		private static final String NEED_TO_WIN = "Z";
		private static final int ROCK_VALUE = 1;
		private static final int PAPER_VALUE = 2;
		private static final int SCISSOR_VALUE = 3;
		private static final int WIN_VALUE = 6;
		private static final int DRAW_VALUE = 3;
		private static final int LOSE_VALUE = 0;

		private final String theirs;
		private final String needTo;

		protected RockPaperScissorRoundTwo(String line) {
			this.theirs = String.valueOf(line.charAt(0));
			this.needTo = String.valueOf(line.charAt(2));
		}

		protected int getScore() {
			if (needTo.equals(NEED_TO_WIN))
				return needToIsWin();
			if (needTo.equals(NEED_TO_DRAW))
				return needToDraw();
			return needToLose();
		}

		private int needToIsWin() {
			int baseScore = WIN_VALUE;
			if (theirs.equals(THEIR_ROCK))
				baseScore += PAPER_VALUE;
			if (theirs.equals(THEIR_PAPER))
				baseScore += SCISSOR_VALUE;
			if (theirs.equals(THEIR_SCISSOR))
				baseScore += ROCK_VALUE;
			return baseScore;
		}

		private int needToDraw() {
			int baseScore = DRAW_VALUE;
			if (theirs.equals(THEIR_ROCK))
				baseScore += ROCK_VALUE;
			if (theirs.equals(THEIR_PAPER))
				baseScore += PAPER_VALUE;
			if (theirs.equals(THEIR_SCISSOR))
				baseScore += SCISSOR_VALUE;
			return baseScore;
		}

		private int needToLose() {
			int baseScore = LOSE_VALUE;
			if (theirs.equals(THEIR_ROCK))
				baseScore += SCISSOR_VALUE;
			if (theirs.equals(THEIR_PAPER))
				baseScore += ROCK_VALUE;
			if (theirs.equals(THEIR_SCISSOR))
				baseScore += PAPER_VALUE;
			return baseScore;
		}
	}

}
