public class RotateMatrix {

	static void rotateMatrix(int[][] arr, int degrees) {	// degrees must be a multiple of 90
		if(degrees < 0) 
			degrees = 360 - (Math.abs(degrees) % 360);

		int N = arr.length;

		for (int layer = 0; layer < N / 2; layer++) {
			int i = layer;
			for (int rotation = 0; rotation < (degrees % 360) / 90; rotation++) {
				for (int j = layer; j < N - i - 1; j++) {
					int temp = arr[i][j];
					arr[i][j] = arr[N - 1 - j][i];
					arr[N - 1 - j][i] = arr[N - 1 - i][N - 1 - j];
					arr[N - 1 - i][N - 1 - j] = arr[j][N - 1 - i];
					arr[j][N - 1 - i] = temp;
				}
			}
		}
	}

	public static void main(String[] args) {
		int[][] arr = incrementalMatrix(4);
		print2DArray( arr );
		System.out.println("\n90° clockwise rotation\n");
		rotateMatrix( arr, 90 );
		print2DArray( arr );

		System.out.println("\n-----------------------------\n");

		arr = incrementalMatrix(4);
		print2DArray( arr );
		System.out.println("\n90° counter-clockwise rotation\n");
		rotateMatrix( arr, -90 );
		print2DArray( arr );

		System.out.println("\n-----------------------------\n");

		arr = incrementalMatrix(5);
		print2DArray( arr );
		System.out.println("\n270° clockwise rotation\n");
		rotateMatrix( arr, 270 );
		print2DArray( arr );
	}

	static void print2DArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) 
				System.out.print(arr[i][j] + " ");
			System.out.println();
		}
	}

	static int[][] incrementalMatrix(int N) {
		int[][] arr = new int[N][N];

		int count = 1;
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < N; j++) 
				arr[i][j] = count++;
		return arr;
	}
}