import java.util.Scanner;

//* �ڹ� ���α׷��� ����
//* �䱸 ����
//  - ���� �˰��� - ���� ����, �� ����, ���� ���� ����(��������)
//  - 1�� Ŭ���� ���
//  - ���� �˰��򺰷� �޼ҵ� �����Ͽ� ����
//  - �ڷᱸ���� �迭�� �̿��ϵ� �����ʹ� 10���� ������ ������
//  - ���� �����ʹ� Scanner Ŭ������ ���� �Է� ���� ��

public class HW01 {

	static int arr[] = new int[10];
	static int arr2[] = new int[10];
	static int arr3[] = new int[10];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("���� 10���� �Է��ϼ���.\n");
		for (int i = 0; i < 10; i++) {
			arr[i] = sc.nextInt();
			arr2[i] = arr[i];
			arr3[i] = arr[i];
		}
		BB_sort(arr);
		insert_sort(arr2);
		Quick_sort(arr3, 0, 9);
		// ����Ʈ ���
		System.out.print("Quick : ");
		for (int i = 0; i < 10; i++) {
			System.out.print(arr3[i] + "  ");
		}
		System.out.print("\n");
	}

	public static void BB_sort(int[] arr) {
		// ���� ��Ʈ
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
		// ��º�
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
		// ��º�
		System.out.print("Insert : ");
		for (int i = 0; i < 10; i++) {
			System.out.print(arr2[i] + "  ");
		}
		System.out.print("\n");
	}

	// ����Ʈ �κ�
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
