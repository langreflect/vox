import groovy.transform.Field

def inorder = new File('inorder.txt')
def preorder = new File('preorder.txt')
def inorderList=[]
def preorderList=[]
@Field String res=""
@Field int count=0
def resultFile = new File('result.txt')


Scanner scanIn = new Scanner(inorder);
while (scanIn.hasNextLong()) {
    long aLong = scanIn.nextLong();
    inorderList << aLong
}

Scanner scanPre = new Scanner(preorder);
while (scanPre.hasNextLong()) {
    long aLong = scanPre.nextLong();
    preorderList << aLong

}




int preStart=0;
int preEnd=preorderList.size()-1;
int inStart=0;
int inEnd=inorderList.size()-1;

TreeNode originalTree=construct(preorderList, preStart, preEnd, inorderList, inStart, inEnd);

println "Leaf nodes:"
printLeafNodes(originalTree)
resultFile.write(res)

TreeNode construct(ArrayList preordered, int preStart, int preEnd, ArrayList inordered, int inStart, int inEnd) {
    if (preStart>preEnd || inStart>inEnd){
        return null;
    }
    println "Constructing:  "+preStart
    long value=preordered[preStart].value
    TreeNode p=new TreeNode(value)
    
    int parentIndex=0
    for (int i=inStart;i<=inEnd;i++){
        if (value==inordered[i]){
            parentIndex=i;
            break;
        }
    }
    p.left = construct(preordered, preStart+1, preStart+(parentIndex-inStart), inordered, inStart, parentIndex-1);
    p.right= construct(preordered, preStart+(parentIndex-inStart)+1, preEnd, inordered, parentIndex+1 , inEnd);

    return p;
}

void printLeafNodes(TreeNode node) {
      if(node == null)       
        return;
      println "Searching for leaves: "+(count++)
       if(node.left == null && node.right==null)      
          res=res+node.value //springbuffer todo
       printLeafNodes(node.left) 
       printLeafNodes(node.right)
}


class TreeNode {
    TreeNode left
    TreeNode right
    long value

    TreeNode (long value){
        this.value=value;
    }
}
