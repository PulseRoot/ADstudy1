import java.util.Scanner;

//* 자바 프로그래밍 과제
//* 요구 사항
//  - 정렬 알고리즘 - 선택 정렬, 퀵 정렬, 버블 정렬 구현(오름차순)
//  - 1개 클래스 사용
//  - 정렬 알고리즘별로 메소드 생성하여 구현
//  - 자료구조는 배열을 이용하되 데이터는 10개의 정수형 데이터
//  - 정렬 데이터는 Scanner 클래스를 통해 입력 받을 것

public class HW01 {

	static int arr[] = new int[10];
	static int arr2[] = new int[10];
	static int arr3[] = new int[10];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("정수 10개를 입력하세요.\n");
		for (int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
			arr2[i] = arr[i];
			arr3[i] = arr[i];
		}
		BB_sort(arr);
		insert_sort(arr2);
		Quick_sort(arr3, 0, 9);
		// 퀵소트 출력
		System.out.print("Quick : ");
		for (int i = 0; i < 10; i++) {
			System.out.print(arr3[i] + "  ");
		}
		System.out.print("\n");
	}

	public static void BB_sort(int[] arr) {
		// 버블 소트
		int temp;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i] < arr[j]) {
					temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}
		// 출력부
		System.out.print("Bubble : ");
		for (int i = 0; i < 10; i++) {
			System.out.print(arr[i] + "  ");
		}
		System.out.print("\n");
	}

	public static void insert_sort(int[] arr) {
		int min;
		int temp;

		for (int i = 0; i < 10; i++) {
			min = i;
			for (int j = i + 1; j < 10; j++) {
				if (arr[min] > arr[j]) {
					min = j;
				}
			}
			temp = arr[min];
			arr[min] = arr[i];
			arr[i] = temp;
		}
		// 출력부
		System.out.print("Insert : ");
		for (int i = 0; i < 10; i++) {
			System.out.print(arr2[i] + "  ");
		}
		System.out.print("\n");
	}

	// 퀵소트 부분
	public static int partition(int[] arr, int p, int r) {
		int pivot = arr[p];
		int temp;
		int h = p;
		for (int k = p + 1; k <= r; k++) {
			if (arr[k] < pivot) {
				h = h + 1;
				temp = arr[h];
				arr[h] = arr[k];
				arr[k] = temp;
			}
		}
		temp = arr[p];
		arr[p] = arr[h];
		arr[h] = temp;

		return h;
	}

	public static void Quick_sort(int[] arr, int p, int r) {
		{
			if (p < r) {
				int q = partition(arr, p, r);
				Quick_sort(arr, p, q - 1);
				Quick_sort(arr, q + 1, r);
			}
		}

	}
}
