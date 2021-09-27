package taller;

public class matriz {

	public static void main(String[] args) {
		String [][] matriz = new String[10][30];
		int A = 0;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 30; j++) {
				if(i < 4 && j > 4 && j < 25) {
					matriz[i][j] = "uno ";
				}else if(i >= 4){
					matriz[i][j] = "uno ";
				}
			}
		}
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 30; j++) {
				System.out.print(matriz[i][j]);
			}
			System.out.println("| Fila "+(i+1));
		}
	}

}
