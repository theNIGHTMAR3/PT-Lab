using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Lab3_CS
{
	//change Car to car in xml
	[XmlType("car")]
	public class Car
	{
		//change motor to engine in xml
		[XmlElement(ElementName = "engine")]
		public Engine motor;
		public string model;
		public int year;
		public Car() { }
		public Car( string model, Engine motor,int year)
		{
			this.model = model;
			this.motor = motor;
			this.year = year;

		}
	}
}
