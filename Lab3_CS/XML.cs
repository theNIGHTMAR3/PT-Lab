using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Linq;
using System.Xml.XPath;

namespace Lab3_CS
{
	class XML
	{

		public static void Task3()
        {
            //load xml file
            XElement rootNode = XElement.Load("CarsCollection.xml");

            //calculate average horse power
            double avgHP = (double)rootNode.XPathEvaluate("sum(//car/engine[@model!=\"TDI\"]/horsePower) div count(//car/engine[@model!=\"TDI\"]/horsePower)");
            Console.WriteLine("Avg HP = "+ avgHP);

            //return models without duplicates
            IEnumerable<XElement> models = rootNode.XPathSelectElements("//car/model[not(. = preceding::model)]");
            Console.WriteLine("All models: ");

            //print all models
            foreach (var model in models)
            {
                Console.WriteLine(model.ToString());
            }
        }

        public static void Task4(List<Car> cars)
        {
            IEnumerable<XElement> nodes = from car in cars
                                          select new XElement("car",
                                                    new XElement("model", car.model),
                                                    new XElement("engine",
                                                                        new XAttribute("model", car.motor.model),
                                                                        new XElement("displacement", car.motor.displacement),
                                                                        new XElement("horsePower", car.motor.horsePower)),
                                                    new XElement("year", car.year)
                                                 );
            XElement rootNode = new XElement("cars", nodes);
            rootNode.Save("CarsLinq.xml");
        }

        public static void Task5(List<Car> cars)
        {
            XElement template = XElement.Load("template.html");
            XElement body = template.Element("{http://www.w3.org/1999/xhtml}body");

            //create table
            IEnumerable<XElement> rows = from car in cars
                                         select new XElement("tr",
                                         new XElement("td", new XAttribute("style", "border: 1px double black"), car.model),
                                         new XElement("td", new XAttribute("style", "border: 1px double black"), car.motor.model),
                                         new XElement("td", new XAttribute("style", "border: 1px double black"), car.motor.displacement),
                                         new XElement("td", new XAttribute("style", "border: 1px double black"), car.motor.horsePower),
                                         new XElement("td", new XAttribute("style", "border: 1px double black"), car.year));

            body.Add(new XElement("table", rows));
            template.Save("cars.html");
        }

        public static void Task6()
        {
            XElement cars = XElement.Load("CarsCollection.xml");

            //hoursePower -> hp
            foreach (var car in cars.Elements())
            {
                foreach (var element in car.Elements())
                {
                    if (element.Name == "engine")
                    {
                        foreach (var engineElement in element.Elements())
                        {
                            if (engineElement.Name == "horsePower")
                            {
                                engineElement.Name = "hp";
                            }
                        }
                    }
                }
            }
            cars.Save("CarsCollectionHP.xml");

            //move year to model and delete it
            foreach (var car in cars.Elements())
            {
                foreach (var element in car.Elements())
                {
                    if (element.Name == "model")
                    {
                        var carYear = car.Element("year");
                        element.Add(new XAttribute("year", carYear.Value));
                        carYear.Remove();
                    }
                }
            }
            cars.Save("CarsCollectionYear.xml");
        }
    }
}
