
using namespace std;

/*
	A looped linked list implemented in C++
	Contains most necessary functions for full use of the structure
*/

template <typename T>   // Forward declaration of the SimpList class
class SimpList;


// Definition of class Node<T>
template <typename T>
class Node                 // Node class for the SimpList class
{
  private:

	  // Data members

	  T           item;   // Node data item
	  Node        *next;  // Pointer to the next node

    // Constructors

    Node () { next = this; }  // default constructor

    // Complete the definition inline

    Node ( const T &initItem, Node<T> *ptr ) 
	{
		item = initItem;
		next = ptr;
	}


  friend class SimpList<T>;
};

// Definition of class SimpList<T>
template < typename T >
class SimpList
{
  public:

    // Constructor (add your code inline)

    SimpList ()
    {
      head = &PHONY;
	  length = 0;
      // complete the data member intialization
    }

    // Destructor (add your code inline)

    ~SimpList () {
		clear();
	}

    // List manipulation operations

    void insert ( const T &newitem );   // insert a data item

    bool remove ( T &target );            // remove data item

    bool find ( T &target ) const;        // find data item

    void clear ();                      // empty the list

    // (add your code inline)
    bool isEmpty () const 
	{
		return (length == 0); // bool
	}

    // length accessor method (add your code inline)
    int size () const 
	{ 
		return length;
	}

    // print the list items
    void print () const;

  private: // data members

    Node<T> PHONY;      // empty node that anchors the list

    Node<T> *head;      // pointer to the beginning of the list

    int length;         // length of list
};

// Implementation section
template <typename T>
void SimpList<T>::print() const
{
  if (length == 0)
  {
    cout << "List is empty." << endl;
    return; 
// May need to add this test to other functions later
  }

  Node<T> *ptr = head->next;
  while (ptr != head)
  {
    cout << ptr->item << "  ";
    ptr = ptr->next;
  }
  cout << endl;
}

template <typename T>
void SimpList<T>::insert(const T &newItem)
{
	Node<T>*current = head;
	Node<T>*newNode = new Node<T>(newItem, head->next);

	while (current->next != head && current->next->item < newNode->item)	
// 1. Make sure not at head 2. Check if next item in list would be bigger
																			// Does not work without head check
	{
		current = current->next;
	}

	newNode->next = current->next;
	current->next = newNode;
	length++;
}

template <typename T>
void SimpList<T>::clear()
{
	while (length > 0)
	{
		Node<T>*target = head->next; // head is immune from deletion
		head->next = target->next;
		delete target;
		length--;
	}
}

template <typename T>
bool SimpList<T>::find(T &target) const
{
	Node<T>*ptr = head; // Starts at the top, goes down the list

	do // Uses a do loop so failing to find target in the head node doesn't cancel it
	{
		if (ptr->item == target)
		{
			return true;
		}

		ptr = ptr->next;
	} while (ptr != head);
	return false;
}

template <typename T>
bool SimpList<T>::remove(T &target)
{

	Node<T>*previousNode = head;
	Node<T>*currentNode = head->next; // Can also be previousNode->next;

	do 
	{
		if (currentNode->item == target)
		{
			Node<T>*currentNode = previousNode->next; // Set currentNode to the right identity
			previousNode->next = previousNode->next->next; // Break link with currentNode, reconnect with whatever's after it
			delete currentNode;
			length--;
			return true;
		}
		previousNode = currentNode;			// Only called if currentNode was not deleted
		currentNode = currentNode->next;	// This does mean that if duplicates are in list, it will only remove the first found
											// Fix: Run find() in a loop to prune targets until it returns false
	} while (currentNode != head);
	
	return false;
}
