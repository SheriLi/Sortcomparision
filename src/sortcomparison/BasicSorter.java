package sortcomparison;

//import static sbcc.Core.*;

import java.util.*;

public class BasicSorter implements Sorter {

	@Override
	public void insertionSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		for (int i = 1; i < numberToSort; ++i) {
			int j = firstIndex + i;
			while (j > firstIndex && data.get(j).compareTo(data.get(j - 1)) < 0) {
				Collections.swap(data, j - 1, j);
				j = j - 1;
			}
		}

	}


	@Override
	public void quickSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		if (numberToSort <= 15) {
			insertionSort(data, firstIndex, numberToSort);
		}

		if (numberToSort > 15) {
			int pIndex = partition(data, firstIndex, numberToSort);
			int numberOfFirst = pIndex - firstIndex;
			int numberOfSecond = numberToSort - numberOfFirst - 1;
			quickSort(data, firstIndex, numberOfFirst); // Before pIndex
			quickSort(data, pIndex + 1, numberOfSecond); // After pIndex
		}

	}


	@Override
	public int partition(ArrayList<String> data, int firstIndex, int numberToPartition) {
		int left = firstIndex + (int) (Math.random() * numberToPartition);
		int right = firstIndex + (int) (Math.random() * numberToPartition);
		int mid = firstIndex + (int) (Math.random() * numberToPartition);

		if (data.get(right).compareTo(data.get(left)) > 0) {
			Collections.swap(data, firstIndex, right);

		} else if (data.get(mid).compareTo(data.get(left)) > 0) {
			Collections.swap(data, firstIndex, mid);

		} else {
			Collections.swap(data, firstIndex, left);
		}

		String pivot = data.get(firstIndex);
		int TooBigNdx = firstIndex + 1;
		int TooSmallNdx = firstIndex + numberToPartition - 1;

		while (TooBigNdx < TooSmallNdx) {
			while (TooBigNdx < TooSmallNdx && data.get(TooBigNdx).compareTo(pivot) <= 0) {
				TooBigNdx++;
			}
			while (TooSmallNdx > firstIndex && data.get(TooSmallNdx).compareTo(pivot) > 0) {
				TooSmallNdx--;
			}
			if (TooBigNdx < TooSmallNdx) {
				Collections.swap(data, TooBigNdx, TooSmallNdx);
			}
		}

		if (pivot.compareTo(data.get(TooSmallNdx)) >= 0) {
			Collections.swap(data, firstIndex, TooSmallNdx);
			return TooSmallNdx;
		} else
			return firstIndex;
	}


	@Override
	public void mergeSort(ArrayList<String> data, int firstIndex, int numberToSort) {

		if (numberToSort <= 15) {
			insertionSort(data, firstIndex, numberToSort);
		} else if (numberToSort > 15) {
			int firstSize = numberToSort / 2;
			int secondSize = numberToSort - firstSize;
			mergeSort(data, firstIndex, firstSize);
			mergeSort(data, firstIndex + firstSize, secondSize);
			merge(data, firstIndex, firstSize, secondSize);
		}
	}


	@Override
	public void merge(ArrayList<String> data, int firstIndex, int leftSegmentSize, int rightSegmentSize) {

		int iFirst = firstIndex;// next element of the 1st half
		int iSecond = firstIndex + leftSegmentSize; // next element of the 2nd half
		int leftIndex = firstIndex + leftSegmentSize; // 1st half's biggest index+1
		int rightIndex = firstIndex + leftSegmentSize + rightSegmentSize;// 2nd half's biggest index+1

		if (data.get(leftIndex - 1).compareTo(data.get(leftIndex)) <= 0) {
			return;
		}

		ArrayList<String> temp = new ArrayList<String>();// temp list for new sorted arraylist

		while (iFirst < leftIndex && iSecond < rightIndex) {
			if (data.get(iFirst).compareTo(data.get(iSecond)) <= 0) {
				temp.add(data.get(iFirst));
				iFirst++;
			} else {
				temp.add(data.get(iSecond));
				iSecond++;
			}
		}
		while (iFirst < leftIndex) {
			temp.add(data.get(iFirst));
			iFirst++;
		}

		for (int i = 0; i < temp.size(); i++) {
			data.set(firstIndex + i, temp.get(i));
		}
	}


	@Override
	public void heapSort(ArrayList<String> data) {
		// TODO Auto-generated method stub

	}


	@Override
	public void heapify(ArrayList<String> data) {
		int curNdx = 0;
		int BigChildNdx;
		boolean bHeap = false;
		while (!bHeap && (2 * curNdx + 2 <= data.size())) {
			if (data.get(2 * curNdx + 1).compareTo(data.get(2 * curNdx + 2)) > 0) {
				BigChildNdx = 2 * curNdx + 1;
			} else {
				BigChildNdx = 2 * curNdx + 2;
			}
			// BigChildNdx = 2 * curNdx + 1;
			if (data.get(curNdx).compareTo(data.get(BigChildNdx)) < 0) {
				Collections.swap(data, curNdx, BigChildNdx);
				curNdx = BigChildNdx;
			} else
				bHeap = true;

		}

	}

}
