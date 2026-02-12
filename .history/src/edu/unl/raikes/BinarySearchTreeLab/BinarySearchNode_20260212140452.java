package edu.unl.raikes.BinarySearchTreeLab;

// Handles looking for nodes and deleting people
class BinarySearchNode {
    protected BinarySearchNode parent;
    protected BinarySearchNode leftChild;
    protected BinarySearchNode rightChild;
    protected Person person;

    // initializes person
    BinarySearchNode(Person person) {
        this.person = person;
    }

    // puts person into tree
    boolean insert(Person data) {
        // if data already exists
        if (data == this.person) {
            return false;
        }
        // checks if left child is empty
        else if (Integer.compare(data.key, person.key) < 0) {
            // if so, set new data
            if (leftChild == null) {
                setLeftChild(new BinarySearchNode(data));
                return true;
            } // if not, keep going down tree
            else {
                return leftChild.insert(data);
            }
        }
        // checks if right child is empty
        else if (Integer.compare(data.key, person.key) > 0) {
            // if so, set new data
            if (rightChild == null) {
                setRightChild(new BinarySearchNode(data));
                return true;
            } // if not, keep going down tree
            else {
                return rightChild.insert(data);
            }
        }
        return false;
    }

    // looks for a specific key in the tree
    BinarySearchNode search(int key) {
        // if left child exists and is < key, return it
        if (leftChild != null && Integer.compare(key, person.key) < 0) {
            return leftChild.search(key);
        }
        // else check right
        else if (rightChild != null && Integer.compare(key, person.key) > 0) {
            return rightChild.search(key);
        }
        // if it is the key, return it
        else if (this.person.key == key) {
            return this;
        }
        // handles exceptions 
        else {
            return null;
        }
    }

    // Deletes person with the key from the tree
    Person delete(int key) {
        // checks the node for key
        BinarySearchNode node = search(key);
        if (node == null)
            return null;
        Person deleted = node.person;

        // if the node has no children, disconnect it from its parent
        if (node.leftChild == null && node.rightChild == null) {
            if (node.parent.leftChild == node)
                node.parent.setLeftChild(null);
            else if (node.parent.rightChild == node)
                node.parent.setRightChild(null);
        }
        // if the node has both children, replace node with the smallest value in the right child tree
        else if (node.leftChild != null && node.rightChild != null) {
            BinarySearchNode min = node.rightChild.getNodeWithMinValue();
            node.person = min.person;
            int minKey = min.person.key;
            min.delete(minKey);
        }
        // if the node has just the left child, replace the parents pointer with the child
        else if (node.parent.leftChild == node) {
            BinarySearchNode newLeftChild = (node.leftChild != null) ? node.leftChild : node.rightChild;
            node.parent.setLeftChild(newLeftChild);
        }
        // if the node has just the right child, replace the parents pointer with the child
        else if (node.parent.rightChild == node) {
            BinarySearchNode newRightChild = (node.leftChild != null) ? node.leftChild : node.rightChild;
            node.parent.setRightChild(newRightChild);
        }

        return deleted;
    }

    // Go through the tree all the way to the left until the next node is null, and then return
    BinarySearchNode getNodeWithMinValue() {
        if (leftChild == null)
            return this;
        else
            return leftChild.getNodeWithMinValue();
    }

    // Sets the left child of a node
    void setLeftChild(BinarySearchNode child) {
        this.leftChild = child;
        if (child != null)
            child.parent = this;
    }

    // sets the right child of a node
    void setRightChild(BinarySearchNode child) {
        this.rightChild = child;
        if (child != null)
            child.parent = this;
    }

    // uses in order search to return a string
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.inOrderToString(sb);
        return sb.toString();
    }
    
    private void inOrderToString(StringBuilder sb) {
        if (this.leftChild != null) {
            this.leftChild.inOrderToString(sb);
        }
    
        sb.append("  ").append(this.person).append("\n");
    
        if (this.rightChild != null) {
            this.rightChild.inOrderToString(sb);
        }
    }
}