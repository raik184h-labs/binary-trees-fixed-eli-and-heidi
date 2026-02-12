package edu.unl.raikes.BinarySearchTreeLab;

// Tree that stores person objects by their keys
public class BinarySearchTree {
	boolean verbose = true;
	private BinarySearchNode root = null;
	private int size = 0;

	// Inserts person into tree based on key
	public void insert(Person data) {
		boolean inserted = false;
		// if tree is empty, new person becomes root
		if (root == null) {
			root = new BinarySearchNode(data);
			inserted = true;
		} // else you look to the root node
		else {
			inserted = root.insert(data);
		} // if you insert, add size
		if (inserted) {
			size++;
		}
	}

	// searches for a person in the tree
	public Person search(int key) {
		// if its empty, return null
		if (root == null) {
			return null;
		}
		// recursively search
		BinarySearchNode found = root.search(key);
		// return person in the found node
		if (found != null) {
			return found.person;
		} else {
			return null;
		}

	}

	// deletes person based on key
	public Person delete(int key) {
		Person deleted = null;

		// if empty, no change
		if (root == null) {
			return null;
		}
		else {
			// if the root has a key, use a temp parent node
			if (root.person.key == key) {
				// add fake root in case the element to be removed is the root.
				// (simplifies pointer logic)
				BinarySearchNode auxRoot = new BinarySearchNode(null);
				auxRoot.setLeftChild(root);
				// delete starting at the og root
				deleted = root.delete(key);
				// retrieve the root from the fake root (in case it changed)
				root = auxRoot.leftChild;
				// make sure the root has no parent
				if (root != null)
					root.parent = null;
			} // if deleting a non root node
			else {
				deleted = root.delete(key);
			} // decrease size if successful
			if (deleted != null)
				size--;
			return deleted;
		}
	}

	// returns a string representation of the tree
	public String toString() {
		String toReturn = "Binary Search Tree of Size: " + size + "\n";
		// if the tree exists, use in order traversal for output
		if (root != null) {
			toReturn += root.toString();
		}
		return toReturn;
	}

}
