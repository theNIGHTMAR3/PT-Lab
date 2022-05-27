using System;
using System.Text;
using System.IO;
using System.Collections.Generic;
using System.Collections;

namespace Lab1_CS
{
	class DirectoryCollection
	{
		//dir
		protected DirectoryClass dir;

		//collection of files and its sizes
		public SortedDictionary<string, int> elements;

		public DirectoryCollection(DirectoryClass dir)
		{
			this.dir = dir;
			elements = new SortedDictionary<string, int>(new MyStringComparer());
			CreateDictionary();

			SerializedCollection serializedColl = new SerializedCollection(this);
		}


		protected void CreateDictionary()
		{
			DirectoryInfo dirInfo = new DirectoryInfo(dir.path);

			var dirChildren = dirInfo.GetDirectories();
			var filesChildren = dirInfo.GetFiles();

			//dirs
			foreach(var child in dirChildren)
			{
				elements.Add(child.Name, (int)(child.GetDirectories().Length +child.GetFiles().Length));
			}

			//files
			foreach (var child in filesChildren)
			{
				elements.Add(child.Name, (int)(child.Length));
			}
		}
	}

}
