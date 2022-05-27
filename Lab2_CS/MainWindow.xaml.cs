using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Forms;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace Lab2_CS
{
	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MainWindow : Window
	{
		//constructor
		public MainWindow()
		{
			InitializeComponent();
		}

		//open
		private void OpenFolder(object sender, RoutedEventArgs e)
		{
			//dialog for choosing folder
			var dlg = new FolderBrowserDialog()
			{
				Description = "Wybierz folder do otwarcia"
			};
			
			//show dialog
			dlg.ShowDialog();
			files.Items.Clear();

			//selected dir into directoryInfo
			DirectoryInfo selectDirectory = new DirectoryInfo(dlg.SelectedPath);

			//create treeview for dir and add as root
			TreeViewItem root = MakeDir(selectDirectory);
			files.Items.Add(root);
		}

		//dir handler in tree view
		private TreeViewItem MakeDir(DirectoryInfo directoryInfo)
		{
			//for every item in view
			var item = new TreeViewItem
			{
				//set header and tag
				Header = directoryInfo.Name,
				Tag = directoryInfo.FullName
			};

			//add menu when clicked
			item.ContextMenu = new System.Windows.Controls.ContextMenu();

			//add crate and delete option for dir
			AddCreateOption(item);
			AddDeleteOption(item);

			//add RAHS to selected item
			item.Selected += new RoutedEventHandler(UpdateRAHS);

			//if has dirs, create all children dirs
			if (directoryInfo.GetDirectories().Length > 0)
			{
				foreach (DirectoryInfo child in directoryInfo.GetDirectories())
				{
					item.Items.Add(MakeDir(child));
				}
			}
			//if has files, create all children files
			if (directoryInfo.GetFiles().Length > 0)
			{
				foreach (FileInfo child in directoryInfo.GetFiles())
				{
					item.Items.Add(MakeFile(child));
				}
			}

			//return each item in tree view
			return item;
		}

		//file handler in tree view
		private TreeViewItem MakeFile(FileInfo fileInfo)
		{
			//for each file set its info
			var item = new TreeViewItem
			{	
				Header = fileInfo.Name,
				Tag = fileInfo.FullName
			};

			//add menu when clicked
			item.ContextMenu = new System.Windows.Controls.ContextMenu();

			//add open and delete options for file
			AddOpenOption(item);
			AddDeleteOption(item);

			//add RAHS for file
			item.Selected += new RoutedEventHandler(UpdateRAHS);
			return item;
		}

		//adds open file option for item
		private void AddOpenOption(TreeViewItem item)
		{
			//creates new menu item
			MenuItem openOption = new MenuItem
			{
				Header = "Open"
			};
			//executes OpenOnClick when clicked
			openOption.Click += new RoutedEventHandler(OpenOnClick);

			//adds open option
			item.ContextMenu.Items.Add(openOption);
		}

		//executes when cliked on item menu
		private void OpenOnClick(object sender, RoutedEventArgs e)
		{
			//return clicked item
			TreeViewItem item = (TreeViewItem)files.SelectedItem;

			//gets files content as a text
			string text = File.ReadAllText((string)item.Tag);

			//prints content on right text block
			fileContent.Content = new TextBlock()
			{
				Text = text
			};
		}

		//add delete option for item
		private void AddDeleteOption(TreeViewItem item)
		{
			//set header
			MenuItem deleteOption = new MenuItem
			{
				Header = "Delete"
			};

			
			deleteOption.Click += new RoutedEventHandler(DeleteOnClick);
			item.ContextMenu.Items.Add(deleteOption);
		}

		//executes deletion on clicked file
		private void DeleteOnClick(object sender, RoutedEventArgs e)
		{

			TreeViewItem item = (TreeViewItem)files.SelectedItem;

			//get RAHS attributes
			FileAttributes fileAttributes = File.GetAttributes((string)item.Tag);
			File.SetAttributes((string)item.Tag, fileAttributes & ~FileAttributes.ReadOnly);

			//if it is file, delete file
			if ((fileAttributes & FileAttributes.Directory) != FileAttributes.Directory)
			{
				File.Delete((string)item.Tag);
			}
			//if dir delete dir
			else
			{
				DeleteDir((string)item.Tag);
			}
			//get items parent and delete its child
			TreeViewItem parent = (TreeViewItem)item.Parent;
			parent.Items.Remove(item);
		}

		//handles dir deletion
		private void DeleteDir(string path)
		{
			DirectoryInfo directoryInfo = new DirectoryInfo(path);
			//if has dirs
			if (directoryInfo.GetDirectories().Length > 0)
			{
				//for each child cascade delete dirs
				foreach (DirectoryInfo child in directoryInfo.GetDirectories())
				{
					DeleteDir(child.FullName);
				}
			}
			//if has files delete all files
			if (directoryInfo.GetFiles().Length > 0)
			{
				foreach (FileInfo file in directoryInfo.GetFiles())
				{
					File.Delete(file.FullName);
				}
			}
			//delete this dir
			Directory.Delete(directoryInfo.FullName);
		}

		//add create option for item
		private void AddCreateOption(TreeViewItem item)
		{
			//appearing create option for dir
			MenuItem createOption = new MenuItem
			{
				Header = "Create"
			};
			createOption.Click += new RoutedEventHandler(CreateOnClick);
			item.ContextMenu.Items.Add(createOption);
		}

		//executes creation for clicke item
		private void CreateOnClick(object sender, RoutedEventArgs e)
		{
			//get its parent
			TreeViewItem parent = (TreeViewItem)files.SelectedItem;

			//creates create dialog for creating new dir or file
			CreationDialog createDialog = new CreationDialog((string)parent.Tag);
			createDialog.ShowDialog();

			//show if dialog is finished
			if (createDialog.finished)
			{
				//if nit existing, create it and set its parent
				if (!File.Exists(createDialog.path))
				{
					DirectoryInfo directoryInfo = new DirectoryInfo(createDialog.path);
					parent.Items.Add(MakeDir(directoryInfo));
				}
				//set file info
				else
				{
					FileInfo fileInfo = new FileInfo(createDialog.path);
					parent.Items.Add(MakeFile(fileInfo));
				}
			}
		}

		//set RAHS for file
		private void UpdateRAHS(object sender, RoutedEventArgs e)
		{
			//rahs
			TreeViewItem item = (TreeViewItem)files.SelectedItem;
			FileAttributes attributes = File.GetAttributes((string)item.Tag);
			string rahs = "";

			//read
			if ((attributes & FileAttributes.ReadOnly) == FileAttributes.ReadOnly)
				rahs += 'r';		
			else
				rahs += '-';

			//archive
			if ((attributes & FileAttributes.Archive) == FileAttributes.Archive)
				rahs += 'a';
			else
				rahs += '-';

			//hidden
			if ((attributes & FileAttributes.Hidden) == FileAttributes.Hidden)
				rahs += 'h';
			else
				rahs += '-';

			//system
			if ((attributes & FileAttributes.System) == FileAttributes.System)
				rahs += 's';
			else
				rahs += '-';

			status.Text = rahs;
		}

		private void Exit(object sender, RoutedEventArgs e)
		{
			Close();
		}
	}
}
