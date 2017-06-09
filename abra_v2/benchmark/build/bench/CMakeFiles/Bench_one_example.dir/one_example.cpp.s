	.file	"one_example.cpp"
	.section	.text.unlikely,"ax",@progbits
.LCOLDB0:
	.text
.LHOTB0:
	.p2align 4,,15
	.type	_ZL16BM_Obstack_allocRN9benchmark5StateE, @function
_ZL16BM_Obstack_allocRN9benchmark5StateE:
.LFB1842:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	pushq	%rbx
	.cfi_def_cfa_offset 24
	.cfi_offset 3, -24
	movq	%rdi, %rbp
	movl	$free, %r8d
	movl	$malloc, %ecx
	xorl	%edx, %edx
	subq	$104, %rsp
	.cfi_def_cfa_offset 128
	xorl	%esi, %esi
	movq	%rsp, %rdi
	call	_obstack_begin
	.p2align 4,,10
	.p2align 3
.L2:
	cmpb	$0, 0(%rbp)
	je	.L15
.L7:
	movq	8(%rbp), %rax
	cmpq	120(%rbp), %rax
	leaq	1(%rax), %rdx
	movq	%rdx, 8(%rbp)
	jnb	.L16
	movq	16(%rbp), %rax
	movq	32(%rsp), %rcx
	movslq	(%rax), %rbx
	movq	24(%rsp), %rax
	movq	%rcx, %rdx
	subq	%rax, %rdx
	cmpq	%rbx, %rdx
	movq	%rbx, %rsi
	jl	.L17
	movq	16(%rsp), %rsi
	addq	%rbx, %rax
	cmpq	%rsi, %rax
	je	.L18
.L3:
	movslq	48(%rsp), %rdi
	movq	%rcx, %rbx
	movq	%rdi, %rdx
	addq	%rdi, %rax
	notl	%edx
	movslq	%edx, %rdx
	andq	%rdx, %rax
	movq	8(%rsp), %rdx
	movq	%rax, %rdi
	movq	%rax, 24(%rsp)
	subq	%rdx, %rdi
	subq	%rdx, %rbx
	cmpq	%rbx, %rdi
	jle	.L4
	movq	%rcx, 24(%rsp)
	movq	%rcx, %rax
.L4:
	movq	%rax, 16(%rsp)
	cmpq	8(%rsp), %rsi
	jbe	.L5
	cmpq	32(%rsp), %rsi
	jnb	.L5
	cmpb	$0, 0(%rbp)
	movq	%rsi, 16(%rsp)
	movq	%rsi, 24(%rsp)
	jne	.L7
.L15:
	movq	%rbp, %rdi
	call	_ZN9benchmark5State16StartKeepRunningEv
	jmp	.L7
	.p2align 4,,10
	.p2align 3
.L5:
	movq	%rsp, %rdi
	call	obstack_free
	jmp	.L2
	.p2align 4,,10
	.p2align 3
.L17:
	movq	%rsp, %rdi
	call	_obstack_newchunk
	movq	24(%rsp), %rax
	movq	16(%rsp), %rsi
	movq	32(%rsp), %rcx
	addq	%rbx, %rax
	cmpq	%rsi, %rax
	jne	.L3
.L18:
	orb	$2, 80(%rsp)
	jmp	.L3
	.p2align 4,,10
	.p2align 3
.L16:
	movq	%rbp, %rdi
	call	_ZN9benchmark5State17FinishKeepRunningEv
	addq	$104, %rsp
	.cfi_def_cfa_offset 24
	popq	%rbx
	.cfi_def_cfa_offset 16
	popq	%rbp
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE1842:
	.size	_ZL16BM_Obstack_allocRN9benchmark5StateE, .-_ZL16BM_Obstack_allocRN9benchmark5StateE
	.section	.text.unlikely
.LCOLDE0:
	.text
.LHOTE0:
	.section	.text.unlikely
.LCOLDB1:
	.text
.LHOTB1:
	.p2align 4,,15
	.type	_ZL9BM_MallocRN9benchmark5StateE, @function
_ZL9BM_MallocRN9benchmark5StateE:
.LFB1841:
	.cfi_startproc
	pushq	%rbx
	.cfi_def_cfa_offset 16
	.cfi_offset 3, -16
	movq	%rdi, %rbx
	jmp	.L20
	.p2align 4,,10
	.p2align 3
.L21:
	movq	8(%rbx), %rax
	cmpq	120(%rbx), %rax
	leaq	1(%rax), %rdx
	movq	%rdx, 8(%rbx)
	jnb	.L25
	movq	16(%rbx), %rax
	movslq	(%rax), %rdi
	call	malloc
	movq	%rax, %rdi
	call	free
.L20:
	cmpb	$0, (%rbx)
	jne	.L21
	movq	%rbx, %rdi
	call	_ZN9benchmark5State16StartKeepRunningEv
	jmp	.L21
	.p2align 4,,10
	.p2align 3
.L25:
	movq	%rbx, %rdi
	popq	%rbx
	.cfi_def_cfa_offset 8
	jmp	_ZN9benchmark5State17FinishKeepRunningEv
	.cfi_endproc
.LFE1841:
	.size	_ZL9BM_MallocRN9benchmark5StateE, .-_ZL9BM_MallocRN9benchmark5StateE
	.section	.text.unlikely
.LCOLDE1:
	.text
.LHOTE1:
	.section	.text.unlikely
.LCOLDB2:
	.section	.text.startup,"ax",@progbits
.LHOTB2:
	.p2align 4,,15
	.globl	main
	.type	main, @function
main:
.LFB1843:
	.cfi_startproc
	pushq	%rbx
	.cfi_def_cfa_offset 16
	.cfi_offset 3, -16
	movq	%rsi, %rbx
	subq	$16, %rsp
	.cfi_def_cfa_offset 32
	movl	%edi, 12(%rsp)
	leaq	12(%rsp), %rdi
	call	_ZN9benchmark10InitializeEPiPPc
	movl	12(%rsp), %edi
	movq	%rbx, %rsi
	call	_ZN9benchmark27ReportUnrecognizedArgumentsEiPPc
	testb	%al, %al
	movl	$1, %edx
	jne	.L27
	call	_ZN9benchmark22RunSpecifiedBenchmarksEv
	xorl	%edx, %edx
.L27:
	addq	$16, %rsp
	.cfi_def_cfa_offset 16
	movl	%edx, %eax
	popq	%rbx
	.cfi_def_cfa_offset 8
	ret
	.cfi_endproc
.LFE1843:
	.size	main, .-main
	.section	.text.unlikely
.LCOLDE2:
	.section	.text.startup
.LHOTE2:
	.section	.rodata.str1.1,"aMS",@progbits,1
.LC3:
	.string	"BM_Malloc"
.LC4:
	.string	"BM_Obstack_alloc"
	.section	.text.unlikely
.LCOLDB5:
	.section	.text.startup
.LHOTB5:
	.p2align 4,,15
	.type	_GLOBAL__sub_I_main, @function
_GLOBAL__sub_I_main:
.LFB2153:
	.cfi_startproc
	.cfi_personality 0x3,__gxx_personality_v0
	.cfi_lsda 0x3,.LLSDA2153
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	pushq	%rbx
	.cfi_def_cfa_offset 24
	.cfi_offset 3, -24
	subq	$8, %rsp
	.cfi_def_cfa_offset 32
.LEHB0:
	call	_ZN9benchmark8internal17InitializeStreamsEv
	movl	$152, %edi
	movl	%eax, _ZN9benchmark8internalL18stream_init_anchorE(%rip)
	call	_Znwm
.LEHE0:
	movl	$.LC3, %esi
	movq	%rax, %rdi
	movq	%rax, %rbx
.LEHB1:
	call	_ZN9benchmark8internal9BenchmarkC2EPKc
.LEHE1:
	movq	%rbx, %rdi
	movq	$_ZTVN9benchmark8internal17FunctionBenchmarkE+16, (%rbx)
	movq	$_ZL9BM_MallocRN9benchmark5StateE, 144(%rbx)
.LEHB2:
	call	_ZN9benchmark8internal25RegisterBenchmarkInternalEPNS0_9BenchmarkE
	movl	$2, %esi
	movq	%rax, %rdi
	call	_ZN9benchmark8internal9Benchmark15RangeMultiplierEi
	movl	$1, %esi
	movl	$16384, %edx
	movq	%rax, %rdi
	call	_ZN9benchmark8internal9Benchmark5RangeEii
	movl	$152, %edi
	movq	%rax, _ZL21_benchmark_2BM_Malloc(%rip)
	call	_Znwm
.LEHE2:
	movl	$.LC4, %esi
	movq	%rax, %rdi
	movq	%rax, %rbx
.LEHB3:
	call	_ZN9benchmark8internal9BenchmarkC2EPKc
.LEHE3:
	movq	%rbx, %rdi
	movq	$_ZTVN9benchmark8internal17FunctionBenchmarkE+16, (%rbx)
	movq	$_ZL16BM_Obstack_allocRN9benchmark5StateE, 144(%rbx)
.LEHB4:
	call	_ZN9benchmark8internal25RegisterBenchmarkInternalEPNS0_9BenchmarkE
	movl	$2, %esi
	movq	%rax, %rdi
	call	_ZN9benchmark8internal9Benchmark15RangeMultiplierEi
	movl	$16384, %edx
	movl	$1, %esi
	movq	%rax, %rdi
	call	_ZN9benchmark8internal9Benchmark5RangeEii
	movq	%rax, _ZL28_benchmark_3BM_Obstack_alloc(%rip)
	addq	$8, %rsp
	.cfi_remember_state
	.cfi_def_cfa_offset 24
	popq	%rbx
	.cfi_def_cfa_offset 16
	popq	%rbp
	.cfi_def_cfa_offset 8
	ret
.L33:
	.cfi_restore_state
.L37:
	movq	%rax, %rbp
	movq	%rbx, %rdi
	call	_ZdlPv
	movq	%rbp, %rdi
	call	_Unwind_Resume
.LEHE4:
.L34:
	jmp	.L37
	.cfi_endproc
.LFE2153:
	.globl	__gxx_personality_v0
	.section	.gcc_except_table,"a",@progbits
.LLSDA2153:
	.byte	0xff
	.byte	0xff
	.byte	0x1
	.uleb128 .LLSDACSE2153-.LLSDACSB2153
.LLSDACSB2153:
	.uleb128 .LEHB0-.LFB2153
	.uleb128 .LEHE0-.LEHB0
	.uleb128 0
	.uleb128 0
	.uleb128 .LEHB1-.LFB2153
	.uleb128 .LEHE1-.LEHB1
	.uleb128 .L33-.LFB2153
	.uleb128 0
	.uleb128 .LEHB2-.LFB2153
	.uleb128 .LEHE2-.LEHB2
	.uleb128 0
	.uleb128 0
	.uleb128 .LEHB3-.LFB2153
	.uleb128 .LEHE3-.LEHB3
	.uleb128 .L34-.LFB2153
	.uleb128 0
	.uleb128 .LEHB4-.LFB2153
	.uleb128 .LEHE4-.LEHB4
	.uleb128 0
	.uleb128 0
.LLSDACSE2153:
	.section	.text.startup
	.size	_GLOBAL__sub_I_main, .-_GLOBAL__sub_I_main
	.section	.text.unlikely
.LCOLDE5:
	.section	.text.startup
.LHOTE5:
	.section	.init_array,"aw"
	.align 8
	.quad	_GLOBAL__sub_I_main
	.local	_ZL28_benchmark_3BM_Obstack_alloc
	.comm	_ZL28_benchmark_3BM_Obstack_alloc,8,8
	.local	_ZL21_benchmark_2BM_Malloc
	.comm	_ZL21_benchmark_2BM_Malloc,8,8
	.local	_ZN9benchmark8internalL18stream_init_anchorE
	.comm	_ZN9benchmark8internalL18stream_init_anchorE,4,4
	.ident	"GCC: (Debian 4.9.2-10) 4.9.2"
	.section	.note.GNU-stack,"",@progbits
