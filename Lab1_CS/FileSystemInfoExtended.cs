using System;
using System.Collections.Generic;
using System.Text;
using System.IO;

namespace Lab1_CS
{
	static class FileSystemInfoExtended
	{
		public static string generateRAHS(this FileSystemInfo fileSystemInfo)
		{
			string rahs = "";

			FileAttributes fileAttributes = fileSystemInfo.Attributes;

			//read
			if ((fileAttributes & FileAttributes.ReadOnly) == FileAttributes.ReadOnly)
				rahs += 'r';
			else
				rahs += '-';

			//archive
			if ((fileAttributes & FileAttributes.Archive) == FileAttributes.Archive)
				rahs += 'a';
			else
				rahs += '-';

			//hidden
			if ((fileAttributes & FileAttributes.Hidden) == FileAttributes.Hidden)
				rahs += 'h';
			else
				rahs += '-';

			//system
			if ((fileAttributes & FileAttributes.System) == FileAttributes.System)
				rahs += 's';
			else
				rahs += '-';

			return rahs;
		}
	}
}
