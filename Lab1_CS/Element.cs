using System;
using System.Collections.Generic;
using System.Text;

namespace Lab1_CS
{
	abstract class Element
	{
		public string path{ get; set; }
		public int depth{ get; set; }
		protected string name { get; set; }

		public abstract void Print();
		
	}
}
