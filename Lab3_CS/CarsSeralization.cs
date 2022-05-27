using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

namespace Lab3_CS
{
	public class CarsSeralization
	{
        CarList carList;
        List<Car> myCars;
        public CarsSeralization(CarList carList)
		{
            this.carList = carList;
            this.myCars = carList.myCars;
		}
		public void Serialize()
		{
            //root change
            XmlSerializer xmlSerializer = new XmlSerializer(typeof(List<Car>), new XmlRootAttribute("cars"));
            var fileName = "CarsCollection.xml";
            var currentDirectory = Directory.GetCurrentDirectory();
            var filePath = Path.Combine(currentDirectory, fileName);
            using (TextWriter writer = new StreamWriter(fileName))
            {
                xmlSerializer.Serialize(writer, this.myCars);
            }
        }
        public List<Car> Deserialize()
        {
            List<Car> cars = new List<Car>();
            XmlSerializer xmlSerializer = new XmlSerializer(typeof(List<Car>), new XmlRootAttribute("cars"));
            using (XmlReader xmlReader = XmlReader.Create("CarsCollection.xml"))
            {
                cars = (List<Car>)xmlSerializer.Deserialize(xmlReader);
            }
            return cars;
        }

        public void Task2()
		{
            //serialization
            Serialize();

            //deserialization
            List<Car> deserializedCars = Deserialize();
		}

    }
}

