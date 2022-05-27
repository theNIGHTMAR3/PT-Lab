using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

namespace Lab1_CS
{
	class File : Element
	{
		protected FileInfo fileInfo;
		public File(string path, int depth)
		{
			this.path = path;
			this.depth = depth;

			string[] tmp = path.Split("\\");

			this.name = tmp[tmp.Length - 1];
			this.fileInfo = new FileInfo(path);
		}

		public override void Print()
		{
			//write tabs for the depth
			for (int i = 0; i < depth; i++)
				Console.Write("\t");

			//write current file
			Console.WriteLine(this.name+" "+this.fileInfo.Length+" bajtow "+this.fileInfo.generateRAHS());

		}
	}
}
