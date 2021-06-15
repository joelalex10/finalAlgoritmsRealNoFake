package Algoritmos.HungarianC;

import static java.lang.Math.*;
import java.util.*;

public class HungarianAlgorithm {
	double[][] array;
	public HungarianAlgorithm(double[][] array){
		this.array = array;
	}
	//********************************//
	//METHODS FOR CONSOLE INPUT-OUTPUT//
	//********************************//


	public int readInput(String prompt)	//Reads input,returns double.
	{
		Scanner in = new Scanner(System.in);
		System.out.print(prompt);
		int input = in.nextInt();
		return input;
	}
	public void printTime(double time)	//Formats time output.
	{
		String timeElapsed = "";
		int days = (int)floor(time)/(24 * 3600);
		int hours = (int)floor(time%(24*3600))/(3600);
		int minutes = (int)floor((time%3600)/60);
		int seconds = (int)round(time%60);

		if (days > 0)
			timeElapsed = Integer.toString(days) + "d:";
		if (hours > 0)
			timeElapsed = timeElapsed + Integer.toString(hours) + "h:";
		if (minutes > 0)
			timeElapsed = timeElapsed + Integer.toString(minutes) + "m:";

		timeElapsed = timeElapsed + Integer.toString(seconds) + "s";
		System.out.print("\nTotal time required: " + timeElapsed + "\n\n");
	}

	//*******************************************//
	//METHODS THAT PERFORM ARRAY-PROCESSING TASKS//
	//*******************************************//

	public void generateRandomArray	//Generates random 2-D array.
	(String randomMethod)
	{
		Random generator = new Random();
		for (int i=0; i<array.length; i++)
		{
			for (int j=0; j<array[i].length; j++)
			{
				if (randomMethod.equals("random"))
					{array[i][j] = generator.nextDouble();}
				if (randomMethod.equals("gaussian"))
				{
						array[i][j] = generator.nextGaussian()/4;		//range length to 1.
						if (array[i][j] > 0.5) {array[i][j] = 0.5;}		//eliminate outliers.
						if (array[i][j] < -0.5) {array[i][j] = -0.5;}	//eliminate outliers.
						array[i][j] = array[i][j] + 0.5;				//make elements positive.
				}
			}
		}
	}
	public double findLargest		//Finds the largest element in a 2D array.
	()
	{
		double largest = Double.NEGATIVE_INFINITY;
		for (int i=0; i<array.length; i++)
		{
			for (int j=0; j<array[i].length; j++)
			{
				if (array[i][j] > largest)
				{
					largest = array[i][j];
				}
			}
		}
			
		return largest;
	}
	public double[][] transpose		//Transposes a double[][] array.
	()
	{
		double[][] transposedArray = new double[array[0].length][array.length];
		for (int i=0; i<transposedArray.length; i++)
		{
			for (int j=0; j<transposedArray[i].length; j++)
			{transposedArray[i][j] = array[j][i];}
		}
		return transposedArray;
	}
	public static double[][] copyOf			//Copies all elements of an array to a new array.
	(double[][] original)
	{
		double[][] copy = new double[original.length][original[0].length];
		for (int i=0; i<original.length; i++)
		{
			//Need to do it this way, otherwise it copies only memory location
			System.arraycopy(original[i], 0, copy[i], 0, original[i].length);
		}

		return copy;
	}
	public double[][] copyToSquare	//Creates a copy of an array, made square by padding the right or bottom.
	(double[][] original, double padValue)
	{
		int rows = original.length;
		int cols = original[0].length;	//Assume we're given a rectangular array.
		double[][] result = null;

		if (rows == cols)	//The matrix is already square.
		{
			result = copyOf(original);
		}
		else if (rows > cols)	//Pad on some extra columns on the right.
		{	
			result = new double[rows][rows];
			for (int i=0; i<rows; i++)
			{
				for (int j=0; j<rows; j++)
				{
					if (j >= cols)	//Use the padValue to fill the right columns.
					{
						result[i][j] = padValue;
					}
					else
					{
						result[i][j] = original[i][j];
					}
				}
			}
		}
		else
		{	// rows < cols; Pad on some extra rows at the bottom.
			result = new double[cols][cols];
			for (int i=0; i<cols; i++)
			{
				for (int j=0; j<cols; j++)
				{
					if (i >= rows)	//Use the padValue to fill the bottom rows.
					{
						result[i][j] = padValue;
					}
					else
					{
						result[i][j] = original[i][j];
					}
				}
			}
		}

		return result;
	}

	//**********************************//
	//MÉTODOS DEL ALGORITMO HÚNGARO//
	//**********************************//

	//Núcleo del algoritmo; toma las entradas requeridas y devuelve las asignaciones
	public int[][] hgAlgorithmAssignments(String sumType)
	{
		// Esta variable se usa para rellenar una matriz rectangular (por lo que se seleccionará todo el último [costo] o el primero [beneficio])
		// y no interferirá con las asignaciones finales. Además, se utiliza para invertir la relación entre pesos.
		// cuando "max" lo define como una matriz de ganancias en lugar de una matriz de costos. Double.MAX_VALUE no es ideal, ya que la aritmética
		// debe realizarse y puede producirse un desbordamiento.
		double maxWeightPlusOne = findLargest() + 1;

		double[][] cost = copyToSquare(array, maxWeightPlusOne);	//Create the cost matrix
		System.out.println("");

		if (sumType.equalsIgnoreCase("max"))	//Then array is a profit array.  Must flip the values because the algorithm finds lowest.
		{
			for (int i=0; i<cost.length; i++)		//Generate profit by subtracting from some value larger than everything.
			{
				for (int j=0; j<cost[i].length; j++)
				{
					cost[i][j] = (maxWeightPlusOne - cost[i][j]);
				}
			}
		}

		int[][] mask = new int[cost.length][cost[0].length];	//The mask array.
		int[] rowCover = new int[cost.length];					//The row covering vector.
		int[] colCover = new int[cost[0].length];				//The column covering vector.
		int[] zero_RC = new int[2];								//Position of last zero from Step 4.
		int[][] path = new int[cost.length * cost[0].length + 2][2];
		int step = 1;
		boolean done = false;
		while (done == false)	//main execution loop
		{
			//System.out.println("EL VARIABLE STEP ES: "+step);
			switch (step)
			{
				case 1:
					step = hg_step1(step, cost);	 
					break;
				case 2:
					step = hg_step2(step, cost, mask, rowCover, colCover);
					break;
				case 3:
					step = hg_step3(step, mask, colCover);
					break;
				case 4:
					step = hg_step4(step, cost, mask, rowCover, colCover, zero_RC);
					break;
				case 5:
					step = hg_step5(step, mask, rowCover, colCover, zero_RC, path);
					break;
				case 6:
					step = hg_step6(step, cost, rowCover, colCover);
					break;
				case 7:
					done=true;
					break;
			}
		}//end while

		int[][] assignments = new int[array.length][2];	//Create the returned array.
		int assignmentCount = 0;	//In a input matrix taller than it is wide, the first
									//assignments column will have to skip some numbers, so
									//the index will not always match the first column ([0])
		/***********
		System.out.println("mask definitivos");
		for (int i=0; i<mask.length; i++){
			for (int j=0; j<mask[i].length; j++){
				System.out.print(mask[i][j]+"\t");
			}
			System.out.println("");
		}****/

		for (int i=0; i<mask.length; i++){
			for (int j=0; j<mask[i].length; j++){
				if (i < array.length && j < array[0].length && mask[i][j] == 1){
					assignments[assignmentCount][0] = i;
					assignments[assignmentCount][1] = j;
					assignmentCount++;
				}
			}
		}

		return assignments;
	}
	//Calls hgAlgorithmAssignments and getAssignmentSum to compute the
	//minimum cost or maximum profit possible.
	public double hgAlgorithm(String sumType)
	{

		return getAssignmentSum(hgAlgorithmAssignments(sumType));


	}
	public double getAssignmentSum(int[][] assignments) {
		//Returns the min/max sum (cost/profit of the assignment) given the
		//original input matrix and an assignment array (from hgAlgorithmAssignments)
		double sum = 0; 
		for (int i=0; i<assignments.length; i++)
		{
			System.out.println("pos["+assignments[i][0]+"]["+assignments[i][1]+"] = "+array[assignments[i][0]][assignments[i][1]]);
			sum = sum + array[assignments[i][0]][assignments[i][1]];

		}
		return sum;
	}
	public static int hg_step1(int step, double[][] cost)
	{
		//¿Qué hace el PASO 1?:
		//Para cada fila de la matriz de costos, encuentre el elemento más pequeño
		//y restarlo de todos los demás elementos de su fila.
		
		double minval;

		for (int i=0; i<cost.length; i++)	
		{									
			minval=cost[i][0];

			for (int j=0; j<cost[i].length; j++)	//1st inner loop finds min val in row.
			{
				if (minval>cost[i][j])
				{
					minval=cost[i][j];
				}
			}
			for (int j=0; j<cost[i].length; j++)	//2nd inner loop subtracts it.
			{
				cost[i][j]=cost[i][j]-minval;
			}
		}




		step=2;
		return step;
	}
	public static int hg_step2(int step, double[][] cost, int[][] mask, int[] rowCover, int[] colCover)
	{
		//Qué hace el PASO 2:
		//Marca los ceros descubiertos como estrellas y cubre su fila y columna.

		for (int i=0; i<cost.length; i++)
		{
			for (int j=0; j<cost[i].length; j++)
			{
				if ((cost[i][j]==0) && (colCover[j]==0) && (rowCover[i]==0))
				{
					mask[i][j]=1;
					colCover[j]=1;
					rowCover[i]=1;
				}
			}
		}



		clearCovers(rowCover, colCover);	//Reset cover vectors.

		step=3;
		return step;
	}
	public static int hg_step3(int step, int[][] mask, int[] colCover)
	{
		//What STEP 3 does:
		//Cover columns of starred zeros. Check if all columns are covered.

		for (int i=0; i<mask.length; i++)	//Cover columns of starred zeros.
		{
			for (int j=0; j<mask[i].length; j++)
			{
				if (mask[i][j] == 1)
				{
					colCover[j]=1;
				}
			}
		}

		int count=0;						
		for (int j=0; j<colCover.length; j++)	//Check if all columns are covered.
		{
			count=count+colCover[j];
		}

		if (count>=mask.length)	//Should be cost.length but ok, because mask has same dimensions.	
		{
			step=7;
		}
		else
		{
			step=4;
		}

		/************System.out.println("mask 3");
		for (int i=0; i<mask.length; i++){

			for (int j=0; j<mask.length; j++){	//1st inner loop finds min val in row.
				System.out.print(mask[i][j]+"\t");
			}
			System.out.println("");
		}***/

		return step;
	}
	public static int hg_step4(int step, double[][] cost, int[][] mask, int[] rowCover, int[] colCover, int[] zero_RC)
	{
		//What STEP 4 does:
		//Find an uncovered zero in cost and prime it (if none go to step 6). Check for star in same row:
		//if yes, cover the row and uncover the star's column. Repeat until no uncovered zeros are left
		//and go to step 6. If not, save location of primed zero and go to step 5.

		int[] row_col = new int[2];	//Holds row and col of uncovered zero.
		boolean done = false;
		while (done == false)
		{
			row_col = findUncoveredZero(row_col, cost, rowCover, colCover);
			if (row_col[0] == -1)
			{
				done = true;
				step = 6;
			}
			else
			{
				mask[row_col[0]][row_col[1]] = 2;	//Prime the found uncovered zero.

				boolean starInRow = false;
				for (int j=0; j<mask[row_col[0]].length; j++)
				{
					if (mask[row_col[0]][j]==1)		//If there is a star in the same row...
					{
						starInRow = true;
						row_col[1] = j;		//remember its column.
					}
				}

				if (starInRow==true)	
				{
					rowCover[row_col[0]] = 1;	//Cover the star's row.
					colCover[row_col[1]] = 0;	//Uncover its column.
				}
				else
				{
					zero_RC[0] = row_col[0];	//Save row of primed zero.
					zero_RC[1] = row_col[1];	//Save column of primed zero.
					done = true;
					step = 5;
				}
			}
		}

		/****************
		System.out.println("datos 4");
		for (int i=0; i<cost.length; i++){

			for (int j=0; j<cost.length; j++){	//1st inner loop finds min val in row.
				System.out.print(cost[i][j]+"\t");
			}
			System.out.println("");
		}
		System.out.println("mask 4");
		for (int i=0; i<mask.length; i++){

			for (int j=0; j<mask.length; j++){	//1st inner loop finds min val in row.
				System.out.print(mask[i][j]+"\t");
			}
			System.out.println("");
		}*********/

		return step;
	}
	public static int[] findUncoveredZero	//Aux 1 for hg_step4.
	(int[] row_col, double[][] cost, int[] rowCover, int[] colCover)
	{
		row_col[0] = -1;	//Just a check value. Not a real index.
		row_col[1] = 0;

		int i = 0; boolean done = false;
		while (done == false)
		{
			int j = 0;
			while (j < cost[i].length)
			{
				if (cost[i][j]==0 && rowCover[i]==0 && colCover[j]==0)
				{
					row_col[0] = i;
					row_col[1] = j;
					done = true;
				}
				j = j+1;
			}//end inner while
			i=i+1;
			if (i >= cost.length)
			{
				done = true;
			}
		}//end outer while

		return row_col;
	}
	public static int hg_step5(int step, int[][] mask, int[] rowCover, int[] colCover, int[] zero_RC, int [][] path)
	{
		//What STEP 5 does:	
		//Construct series of alternating primes and stars. Start with prime from step 4.
		//Take star in the same column. Next take prime in the same row as the star. Finish
		//at a prime with no star in its column. Unstar all stars and star the primes of the
		//series. Erasy any other primes. Reset covers. Go to step 3.

		int count = 0;										//Counts rows of the path matrix.
		//int[][] path = new int[(mask[0].length + 2)][2];	//Path matrix (stores row and col).
		path[count][0] = zero_RC[0];						//Row of last prime.
		path[count][1] = zero_RC[1];						//Column of last prime.

		boolean done = false;
		while (done == false)
		{ 
			int r = findStarInCol(mask, path[count][1]);
			if (r>=0)
			{
				count = count+1;
				path[count][0] = r;					//Row of starred zero.
				path[count][1] = path[count-1][1];	//Column of starred zero.
			}
			else
			{
				done = true;
			}
			
			if (done == false)
			{
				int c = findPrimeInRow(mask, path[count][0]);
				count = count+1;
				path[count][0] = path[count-1][0];	//Row of primed zero.
				path[count][1] = c;					//Col of primed zero.
			}
		}//end while

		convertPath(mask, path, count);
		clearCovers(rowCover, colCover);
		erasePrimes(mask);

		step = 3;
		return step;
		
	}
	public static int findStarInCol			//Aux 1 for hg_step5.
	(int[][] mask, int col)
	{
		int r = -1;	//Again this is a check value.
		for (int i=0; i<mask.length; i++)
		{
			if (mask[i][col]==1)
			{
				r = i;
			}
		}

		return r;
	}
	public static int findPrimeInRow		//Aux 2 for hg_step5.
	(int[][] mask, int row)
	{
		int c = -1;
		for (int j=0; j<mask[row].length; j++)
		{
			if (mask[row][j]==2)
			{
				c = j;
			}
		}
		
		return c;
	}
	public static void convertPath			//Aux 3 for hg_step5.
	(int[][] mask, int[][] path, int count)
	{
		for (int i=0; i<=count; i++)
		{
			if (mask[path[i][0]][path[i][1]]==1)
			{
				mask[path[i][0]][path[i][1]] = 0;
			}
			else
			{
				mask[path[i][0]][path[i][1]] = 1;
			}
		}
	}
	public static void erasePrimes			//Aux 4 for hg_step5.
	(int[][] mask)
	{
		for (int i=0; i<mask.length; i++)
		{
			for (int j=0; j<mask[i].length; j++)
			{
				if (mask[i][j]==2)
				{
					mask[i][j] = 0;
				}
			}
		}
	}
	public static void clearCovers			//Aux 5 for hg_step5 (and not only).
	(int[] rowCover, int[] colCover)
	{
		for (int i=0; i<rowCover.length; i++)
		{
			rowCover[i] = 0;
		}
		for (int j=0; j<colCover.length; j++)
		{
			colCover[j] = 0;
		}
	}
	public static int hg_step6(int step, double[][] cost, int[] rowCover, int[] colCover)
	{
		//What STEP 6 does:
		//Find smallest uncovered value in cost: a. Add it to every element of covered rows
		//b. Subtract it from every element of uncovered columns. Go to step 4.

		double minval = findSmallest(cost, rowCover, colCover);

		for (int i=0; i<rowCover.length; i++)
		{
			for (int j=0; j<colCover.length; j++)
			{
				if (rowCover[i]==1)
				{
					cost[i][j] = cost[i][j] + minval;
				}
				if (colCover[j]==0)
				{
					cost[i][j] = cost[i][j] - minval;
				}
			}
		}
			
		step = 4;
		return step;
	}
	public static double findSmallest		//Aux 1 for hg_step6.
	(double[][] cost, int[] rowCover, int[] colCover)
	{
		double minval = Double.POSITIVE_INFINITY;	//There cannot be a larger cost than this.
		for (int i=0; i<cost.length; i++)		//Now find the smallest uncovered value.
		{
			for (int j=0; j<cost[i].length; j++)
			{
				if (rowCover[i]==0 && colCover[j]==0 && (minval > cost[i][j]))
				{
					minval = cost[i][j];
				}
			}
		}
		
		return minval;
	}
	public String[][] matString(String sumType){
		String[][] mat = new String[array.length][array.length];
		int [][] arrPos = hgAlgorithmAssignments(sumType);
		for(int i=0; i<array.length; i++) {
			for(int j=0; j<array.length; j++){
				mat[i][j] = String.valueOf(array[i][j]);
			}
		}

		for (int i=0; i<arrPos.length; i++){
			mat[arrPos[i][0]][arrPos[i][1]] = "("+mat[arrPos[i][0]][arrPos[i][1]]+")";
		}
		return mat;
	}

	public double[][]restas(String sumType, boolean isChange){
		double arrRestas[]= new double[array.length];
		double matRestas[][] = new double[array.length][array.length];
		switch (sumType){
			case "max":
				double valorMaxF=0;
				for(int i=0; i<array.length; i++) {
					valorMaxF = array[0][i];
					for(int j=0; j<array.length; j++){
						if(valorMaxF<array[j][i]) {
							valorMaxF=array[j][i];
						}
					}
					arrRestas[i]=valorMaxF;
				}
				for(int i=0;i<matRestas.length;i++){
					for(int j=0;j<matRestas.length;j++){
						matRestas[i][j] =  array[i][j] - arrRestas[j];
					}
				}

				System.out.println("rest");
				for(int i=0;i<matRestas.length;i++){
					for(int j=0;j<matRestas.length;j++){
						System.out.print(matRestas[i][j]+"\t");
					}

					System.out.println("");
				}

				if(isChange){
                    double arrRestas2[]= new double[array.length];
                    double valorMax2=0;

                    for(int i=0;i<matRestas.length;i++){
                        valorMax2 = matRestas[i][0];
                        for(int j=0;j<matRestas.length;j++){
                            if(valorMax2<matRestas[i][j]) {
                                valorMax2=matRestas[i][j];
                            }
                        }
                        System.out.println("El valor max de la fila " + i + " es " + valorMax2);
                        arrRestas2[i] = valorMax2;
                    }
                    for(int i=0;i<matRestas.length;i++){
                        for(int j=0;j<matRestas.length;j++){
                            matRestas[i][j] =  matRestas[i][j] - arrRestas2[i];
                        }
                    }

                    System.out.println("rest2");
                    for(int i=0;i<matRestas.length;i++){
                        for(int j=0;j<matRestas.length;j++){
                            System.out.print(matRestas[i][j]+"\t");
                        }

                        System.out.println("");
                    }


				    System.out.println("SE HARA MAXIMO DE FILAS");
                }


				break;
			case "min":
				double valorMinF=0;
				for(int i=0; i<array.length; i++) {
					valorMinF = array[0][i];
					for(int j=0; j<array.length; j++){
						if(valorMinF>array[j][i]) {
							valorMinF=array[j][i];
						}
					}
					arrRestas[i]=valorMinF;
				}
				for(int i=0;i<arrRestas.length;i++){
					for(int j=0;j<matRestas.length;j++){
						matRestas[i][j] = arrRestas[j]- array[i][j];
					}
				}

				System.out.println("");
				for(int i=0;i<matRestas.length;i++){
					for(int j=0;j<matRestas.length;j++){
						System.out.print(matRestas[i][j]+"\t");
					}

					System.out.println("");
				}

                if(isChange){
                    double arrRestas2[]= new double[array.length];
                    double valorMin2=0;

                    for(int i=0;i<matRestas.length;i++){
                        valorMin2 = matRestas[i][0];
                        for(int j=0;j<matRestas.length;j++){
                            if(valorMin2<matRestas[i][j]) {
                                valorMin2=matRestas[i][j];
                            }
                        }
                        System.out.println("El valor min de la fila " + i + " es " + valorMin2);
                        arrRestas2[i] = valorMin2;
                    }
                    for(int i=0;i<matRestas.length;i++){
                        for(int j=0;j<matRestas.length;j++){
                            matRestas[i][j] =   arrRestas2[i] - matRestas[i][j];
                        }
                    }

                    System.out.println("rest2");
                    for(int i=0;i<matRestas.length;i++){
                        for(int j=0;j<matRestas.length;j++){
                            System.out.print(matRestas[i][j]+"\t");
                        }

                        System.out.println("");
                    }


                    System.out.println("SE HARA MINIMO DE FILAS");
                }

                break;
		}



		return matRestas;
	}

	public static void set(double [][] arr, int i, int j, double v) {arr[i][j] = v;}

	//***********//
	//MAIN METHOD//
	//***********//



}