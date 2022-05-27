using System;
using System.Text;
using System.IO;
using System.Collections.Generic;
using System.Collections;

namespace Lab1_CS
{
    class Program
    {
        static void Main(string[] args)
        {
            //dir in arguments
            string folder = args[0];

            //begin with start directory and 0 depth
            DirectoryClass rootDir = new DirectoryClass(folder,0);

            //print root content
            rootDir.Print();

            //root folder info
            DirectoryInfo dirInfo = new DirectoryInfo(folder);

            //find olders file
            FileInfo fileInfo = dirInfo.findOldestFile();
            Console.WriteLine("\n\nNajstarszy plik: " + fileInfo.CreationTime+"\n\n");

            //handle collection
            DirectoryCollection directoryCollection = new DirectoryCollection(rootDir);

        }
    }
}
