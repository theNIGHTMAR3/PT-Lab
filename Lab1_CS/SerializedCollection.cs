using System;
using System.Collections.Generic;
using System.Text;
using System.IO;
using System.Runtime.Serialization.Formatters.Binary;

namespace Lab1_CS
{
	class SerializedCollection
	{
		private DirectoryCollection dirCollection;

		public SerializedCollection(DirectoryCollection collection)
		{
			this.dirCollection = collection;

			//perform serialization and deserialization
			SerializeCollection();
			DeserializeCollection();
			
		}
		private void SerializeCollection()
		{
			BinaryFormatter binFormatter = new BinaryFormatter();
			using (Stream s = new FileStream("serialization.txt", FileMode.Create, FileAccess.Write))
			{
				binFormatter.Serialize(s, dirCollection.elements);
				s.Close();
			}
		}

		private void DeserializeCollection()
		{
			SortedDictionary<string, int> deserializedColl = new SortedDictionary<string, int>(new MyStringComparer());

			FileStream fStream = new FileStream("serialization.txt", FileMode.Open, FileAccess.Read);
			Stream stream = fStream;

			BinaryFormatter format = new BinaryFormatter();

			deserializedColl = (SortedDictionary<string, int>)format.Deserialize(stream);

			foreach (var file in deserializedColl)
			{
				Console.WriteLine(file.Key +" -> "+ file.Value);
			}

			stream.Close();
			fStream.Close();
		}

		
	}
}
