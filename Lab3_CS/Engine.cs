using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Lab3_CS
{
    //change model to engine in xml
    [XmlRoot(ElementName = "engine")]
    public class Engine
	{
        [XmlAttribute]
        public string model;
        public double displacement;
        public double horsePower;
        public Engine() { }

        public Engine(double displacement, double horsePower, string model)
        {
            this.displacement = displacement;
            this.horsePower = horsePower;
            this.model = model;
        }
    }
}
