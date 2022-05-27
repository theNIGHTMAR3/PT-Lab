using System;
using System.Text;
using System.IO;
using System.Collections.Generic;
using System.Collections;

namespace Lab1_CS
{
	class DirectoryClass : Element
	{
		//protected DirectoryCollection dirColl;
		protected DirectoryInfo dirInfo;

		public DirectoryClass(string path, int depth)
		{
			this.path = path;
			this.depth = depth;

			//extracting name from path
			string[] temp = path.Split("\\");
			this.name = temp[temp.Length - 1];

			this.dirInfo = new DirectoryInfo(path);
		}

		//print all children
		public override void Print()
		{
			//getting files and dirs in this folder
			var dirChildren = dirInfo.GetDirectories();
			var filesChildren = dirInfo.GetFiles();

			int depthTemp = depth;

			//depth==0 -> root
			if(depthTemp==0)
			{
				dirChildren = dirInfo.GetDirectories();
				filesChildren = dirInfo.GetFiles();

				//amount of dirs and files
				int size = dirChildren.Length + filesChildren.Length;

				//name size RAHS
				Console.Write(name+" "+size+" "+ dirInfo.generateRAHS());
				depthTemp++;
			}

			//print files and dirs
			printDirectories(depthTemp);
			printFiles(depthTemp);
			
		}

		//print all folders in this dir
		private void printDirectories(int tabs)
		{
			//gets array of all folders in this dir
			string[] dirs=Directory.GetDirectories(path);


			foreach(var dir in dirs)
			{
				string[] temp = dir.Split("\\");
				//folder name
				string folderName = temp[temp.Length - 1];

				DirectoryInfo dirInfo = new DirectoryInfo(dir);

				//getting files and dirs in this next folder
				var dirChildren = dirInfo.GetDirectories();
				var filesChildren = dirInfo.GetFiles();

				//print tabs
				for(int i=0;i<tabs;i++)
					Console.Write("\t");

				//amount of dirs and files
				int size = dirChildren.Length + filesChildren.Length;

				//name size RAHS
				Console.WriteLine(folderName+" ("+size+") "+dirInfo.generateRAHS());

				//recursively print cuntents of this folder
				DirectoryClass nextDir = new DirectoryClass(dir, tabs++);
				nextDir.Print();

			}
		}

		//print all files in this dir
		private void printFiles(int tabs)
		{
			//gets array fo files in this dir
			string[] files = Directory.GetFiles(path);

			//prints each of the files
			foreach(var file in files)
			{
				File temp = new File(file, tabs);
				temp.Print();
			}
		}


	}
}
