using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3_CS
{
	class Program
	{
		static void Main(string[] args)
		{
			CarList cars = new CarList();

			LINQ.Task1(cars);

			Console.WriteLine("\n");

			new CarsSeralization(cars).Task2();


			XML.Task3();

			Console.WriteLine("\n");

			XML.Task5(cars.myCars);

			XML.Task6();
		}
	}
}
