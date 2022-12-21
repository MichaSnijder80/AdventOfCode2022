package org.micha.apps.adventofcode2022.days;

import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.micha.apps.adventofcode2022.exceptions.AdventOfCodeException;

import lombok.Getter;

public class DayFive extends AdventDayRunner<String, String> {

	protected DayFive(String fileName, String identifierOfDay) throws AdventOfCodeException {
		super(fileName, identifierOfDay);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String partOne() throws AdventOfCodeException {
		int indexOfBreak = this.getData().indexOf("");
		final List<String> cargoPlatformStrings = this.getData().subList(0, indexOfBreak);
		final List<String> craneOrdersStrings = this.getData().subList(indexOfBreak + 1, this.getData().size());
		final CargoPlatform cargoPlatform = new CargoPlatform(cargoPlatformStrings);
		craneOrdersStrings.stream().map(CraneOrder::new).forEach(cargoPlatform::processOrderCrateMover9000);
		return cargoPlatform.cargoPlatformConfiguration.stream()
				.map(column -> column.get(0)).collect(joining());
	}

	@Override
	public String partTwo() throws AdventOfCodeException {
		int indexOfBreak = this.getData().indexOf("");
		final List<String> cargoPlatformStrings = this.getData().subList(0, indexOfBreak);
		final List<String> craneOrdersStrings = this.getData().subList(indexOfBreak + 1, this.getData().size());
		final CargoPlatform cargoPlatform = new CargoPlatform(cargoPlatformStrings);
		craneOrdersStrings.stream().map(CraneOrder::new).forEach(cargoPlatform::processOrderCrateMover9001);
		return cargoPlatform.cargoPlatformConfiguration.stream()
				.map(column -> column.get(0)).collect(joining());
	}
	
	private static class CargoPlatform {
		private final List<List<String>> cargoPlatformConfiguration;
		
		private CargoPlatform(List<String> lines) {
			this.cargoPlatformConfiguration = new ArrayList<>();
			String bottomline = lines.get(lines.size() - 1);
			int columnCount = getColumnCount(bottomline);
			IntStream.range(0, columnCount).forEach(i -> this.cargoPlatformConfiguration.add(new ArrayList<>()));
			fillPlatform(lines.subList(0, lines.size() - 1));
		}
		
		private int getColumnCount(String line) {
			int columnCount = 0;
			for(int i = 1; i < line.length(); i +=4) {
				columnCount++;
			}
			return columnCount;
		}
		
		private void fillPlatform(List<String> lines) {
			for(int i = 0; i < lines.size(); i++) {
				for(int j = 0; 4*j + 1 < lines.get(i).length(); j++) {
					final int index = 4*j + 1;
					final String line = lines.get(i);
					final String box = line.substring(index, index + 1);
					if(box.equals(" ")) continue;
					this.cargoPlatformConfiguration.get(j).add(box);
				}
			}
		}
		
		private void processOrderCrateMover9000(CraneOrder craneOrder) {
			for(int i = 0; i < craneOrder.amount; i++) {
				String boxToBeMoved = this.cargoPlatformConfiguration.get(craneOrder.from - 1).remove(0);
				this.cargoPlatformConfiguration.get(craneOrder.to - 1).add(0, boxToBeMoved);
			}
		}
		
		private void processOrderCrateMover9001(CraneOrder craneOrder) {
			List<String> boxesToBeMoved = new ArrayList<>();
			for(int i = 0; i < craneOrder.amount; i++) {
				String boxToBeMoved = this.cargoPlatformConfiguration.get(craneOrder.from - 1).remove(0);
				boxesToBeMoved.add(boxToBeMoved);
			}
			for(int j = boxesToBeMoved.size() - 1; j >= 0; j--) {
				this.cargoPlatformConfiguration.get(craneOrder.to - 1).add(0, boxesToBeMoved.get(j));
			}
		}
	}
	
	@Getter
	private static class CraneOrder {
		private final int amount;
		private final int from;
		private final int to;
		
		private CraneOrder(String line) {
			Matcher matcher = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)").matcher(line);
			matcher.find();
			this.amount = Integer.valueOf(matcher.group(1));
			this.from = Integer.valueOf(matcher.group(2));
			this.to = Integer.valueOf(matcher.group(3));
		}
	}

}
