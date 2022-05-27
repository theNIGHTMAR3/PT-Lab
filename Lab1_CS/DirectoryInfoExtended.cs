using System;
using System.Text;
using System.IO;

namespace Lab1_CS
{
	static class DirectoryInfoExtended
	{
		//find oldest file
		public static FileInfo findOldestFile(this DirectoryInfo dirInfo)
		{
			FileInfo old;

			FileInfo[] children = dirInfo.GetFiles();

			//first oldest os first one
			old = children[0];

			foreach(FileInfo child in children)
			{
				if (child.CreationTime > old.CreationTime)
					old = child;
			}

			return old;
		}
	}
}
