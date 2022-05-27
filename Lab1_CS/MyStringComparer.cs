using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;

namespace Lab1_CS
{
	[Serializable]
	class MyStringComparer : IComparer<string>
	{
		public int Compare([AllowNull] string x, [AllowNull] string y)
		{
			//calculate difference between their sizes
			int difference = x.Length - y.Length;

			//if equal, return CompareTo function
			if (difference == 0)
			{
				return x.CompareTo(y);
			}
			//if not, return this difference
			else return difference;
		}
	}
}