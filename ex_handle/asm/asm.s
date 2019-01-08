	.text
	.file	"asm.c"
	.globl	main                    # -- Begin function main
	.p2align	4, 0x90
	.type	main,@function
main:                                   # @main
	.cfi_startproc
# %bb.0:
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset %rbp, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register %rbp
	subq	$16, %rsp
	movl	$0, -4(%rbp)
	#APP
	jmp	label
	#NO_APP
	movabsq	$.L.str, %rdi
	callq	puts
	#APP
label:
	#NO_APP
	xorl	%ecx, %ecx
	movl	%eax, -8(%rbp)          # 4-byte Spill
	movl	%ecx, %eax
	addq	$16, %rsp
	popq	%rbp
	.cfi_def_cfa %rsp, 8
	retq
.Lfunc_end0:
	.size	main, .Lfunc_end0-main
	.cfi_endproc
                                        # -- End function
	.type	x,@object               # @x
	.data
	.globl	x
	.p2align	2
x:
	.long	4                       # 0x4
	.size	x, 4

	.type	.L.str,@object          # @.str
	.section	.rodata.str1.1,"aMS",@progbits,1
.L.str:
	.asciz	"You should not see this."
	.size	.L.str, 25


	.ident	"clang version 7.0.1-+rc3-2 (tags/RELEASE_701/rc3)"
	.section	".note.GNU-stack","",@progbits
	.addrsig
	.addrsig_sym main
	.addrsig_sym puts
	.addrsig_sym x
