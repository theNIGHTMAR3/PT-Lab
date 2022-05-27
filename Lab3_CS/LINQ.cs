using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab3_CS
{
	public class LINQ
	{
		public static void Task1(CarList carsDB)
		{
			//get cars from DB
			List<Car> cars = carsDB.myCars;

			//query 1
			var query1 = cars.Where(c => c.model.Equals("A6")) //look for A6 model
							.Select(car => new
							{ 
								//if motor model is TDI, engineType=diesel, alse engineType=petrol
								engineType = String.Compare(car.motor.model, "TDI") == 0 ? "diesel" : "petrol",
								//hppl=horsePower/displacement
								hppl = car.motor.horsePower / car.motor.displacement, 
							});

			//print whole query
			foreach(var element in query1)
			{
				Console.WriteLine(element.ToString());
			}

			//query 2
			var query2 = query1.GroupBy(c => c.engineType)	//group hppl by engineType
							 .Select(e => $"{ e.First().engineType}:" +
								$" { e.Average(s => s.hppl).ToString()}");

			//print whole query
			foreach(var element in query2)
			{
				Console.WriteLine(element.ToString());
			}

		}
	}
}
