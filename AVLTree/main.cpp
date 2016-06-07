#include <stdio.h>
#include <stdlib.h>

#define max(a,b)    (((a) > (b)) ? (a) : (b))
/*
由于发现java使用过程中递归的缺点，
转而使用c++
这次是第一次使用
主要抄袭了别人的代码
很失败
*/
typedef struct AvlNode{
    int data;
    struct AvlNode *left_child, *right_child;
} AvlNode;

AvlNode *root;

/*    旋转动作开始        */
AvlNode *rotate_LL(AvlNode *parent){
    AvlNode *child = parent->left_child;
    parent->left_child = child->right_child;
    child->right_child = parent;
    return child;
}

AvlNode *rotate_RR(AvlNode *parent){
    AvlNode *child = parent->right_child;
    parent->right_child = child->left_child;
    child->left_child = parent;
    return child;
}

AvlNode *rotate_RL(AvlNode *parent){
    AvlNode *child = parent->right_child;
    parent->right_child = rotate_LL(child);
    return rotate_RR(parent);
}

AvlNode *rotate_LR(AvlNode *parent){
    AvlNode *child = parent->left_child;
    parent->left_child = rotate_RR(child);
    return rotate_LL(parent);
}
/*    旋转动作结束    */

int get_height(AvlNode *node){
    int height = 0;
    if(node != NULL){
        height = 1 + max(get_height(node->left_child), get_height(node->right_child));
    }
    return height;
}

int get_balance(AvlNode *node){
    if(node == NULL) return 0;
    return get_height(node->left_child) - get_height(node->right_child);
}

/*    平衡二叉树    */
AvlNode *balance_tree(AvlNode **node){
    int height_diff = get_balance(*node); /* 平衡因子在-1到1之间*/

    if(height_diff > 1){
        if(get_balance((*node)->left_child) > 0){
            *node = rotate_LL(*node);
        }else{
            *node = rotate_LR(*node);
        }
    }else if(height_diff < -1){
        if(get_balance((*node)->right_child) < 0){
            *node = rotate_RR(*node);
        }else{
            *node = rotate_RL(*node);
        }
    }
    return *node;
}

AvlNode *avl_add(AvlNode **root, int key){
    if(*root == NULL){
        *root = (AvlNode *)malloc(sizeof(AvlNode));
        if(*root == NULL){
            printf("内存分配失败!\n");
            exit(-1);
        }

        (*root)->data = key;
        (*root)->left_child = (*root)->right_child = NULL;
    }else if(key < (*root)->data){
        (*root)->left_child = avl_add(&((*root)->left_child), key);
        //balance_tree(root);
    }else if(key > (*root)->data){
        (*root)->right_child = avl_add(&((*root)->right_child), key);
        //balance_tree(root);
    }else{
        printf("复制%d失败!\n", key);
        exit(-1);
    }

    return *root;
}

AvlNode *avl_print(AvlNode *node){
    if(node == NULL) return NULL;

    printf("%d->", node->data);

    avl_print(node->left_child);
    avl_print(node->right_child);
    return node;
}

int main(){
    avl_add(&root, 24);
    avl_add(&root, 17);
    avl_add(&root, 40);
    avl_add(&root, 8);
    avl_add(&root, 10);
    avl_add(&root, 18);
    avl_add(&root, 23);

    printf("打印二叉树\n");
    avl_print(root);
    printf("\n");

    balance_tree(&root);
    printf("打印二叉树\n");
    avl_print(root);
    printf("\n");
    return 0;
}
